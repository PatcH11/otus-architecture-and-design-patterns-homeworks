package ru.evsyukov.otus;

import org.junit.jupiter.api.Test;
import ru.evsyukov.otus.commands.Command;
import ru.evsyukov.otus.commands.impl.RetryCommand;
import ru.evsyukov.otus.commands.impl.SecondRetryCommand;
import ru.evsyukov.otus.commands.impl.WriteToLogExceptionCommand;
import ru.evsyukov.otus.exceptions.handlers.ExceptionHandler;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;

public class EventLoopTest {

    // [command, exception] -> RetryCommand -> [retryCommand, exception] -> WriteToLogExceptionCommand
    @Test
    public void givenCommand_whenRe_thenSuccess() {
        // Given
        var command = mock(Command.class);
        var commandQueue = new LinkedList<>(List.of(command));

        doThrow(RuntimeException.class).when(command).execute();

        var exceptionHandler = new ExceptionHandler();
        var eventLoop = new EventLoop(commandQueue, exceptionHandler);

        exceptionHandler.registerHandler(
                command.getClass(),
                RuntimeException.class,
                (c, e) -> () -> commandQueue.add(new RetryCommand(command))
        );

        exceptionHandler.registerHandler(
                RetryCommand.class,
                RuntimeException.class,
                (c, e) -> () -> commandQueue.add(new WriteToLogExceptionCommand(e))
        );

        // When
        eventLoop.update();

        // Then
        verify(command, times(2)).execute();
    }

    @Test
    public void givenCommand_whenRetryRetryLog_thenSuccess() {
        // Given
        var command = mock(Command.class);
        var commandQueue = new LinkedList<>(List.of(command));

        doThrow(RuntimeException.class).when(command).execute();

        var exceptionHandler = new ExceptionHandler();
        var eventLoop = new EventLoop(commandQueue, exceptionHandler);

        exceptionHandler.registerHandler(
                command.getClass(),
                RuntimeException.class,
                (c, e) -> () -> commandQueue.add(new RetryCommand(command))
        );

        exceptionHandler.registerHandler(
                RetryCommand.class,
                RuntimeException.class,
                (c, e) -> () -> commandQueue.add(new SecondRetryCommand(c))
        );

        exceptionHandler.registerHandler(
                SecondRetryCommand.class,
                RuntimeException.class,
                (c, e) -> () -> commandQueue.add(new WriteToLogExceptionCommand(e))
        );

        // When
        eventLoop.update();

        // Then
        verify(command, times(3)).execute();
    }
}

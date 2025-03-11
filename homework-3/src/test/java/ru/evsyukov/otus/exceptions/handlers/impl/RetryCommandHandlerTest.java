package ru.evsyukov.otus.exceptions.handlers.impl;

import org.junit.jupiter.api.Test;
import ru.evsyukov.otus.commands.Command;
import ru.evsyukov.otus.commands.impl.RetryCommand;
import ru.evsyukov.otus.exceptions.handlers.impl.RetryCommandHandler;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class RetryCommandHandlerTest {

    @Test
    public void givenCommand_whenApply_thenAddedToQueue() {
        // Given
        var command = mock(Command.class);
        var queue = spy(new LinkedList<Command>());
        var handler = spy(new RetryCommandHandler(queue, command));

        assertTrue(queue.isEmpty());

        // When
        handler.execute();

        // Then
        assertFalse(queue.isEmpty());
        verify(queue, only()).add(any(RetryCommand.class));
    }
}

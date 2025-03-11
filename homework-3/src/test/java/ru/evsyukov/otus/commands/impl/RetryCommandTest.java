package ru.evsyukov.otus.commands.impl;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.evsyukov.otus.commands.Command;

import static org.mockito.Mockito.*;

public class RetryCommandTest {

    @Test
    public void givenCommand_whenExecute_thenCommandExecuted() {
        // Given
        var command = Mockito.mock(Command.class);
        var retryCommand = spy(new RetryCommand(command));

        // When
        retryCommand.execute();

        // Then
        verify(retryCommand, only()).execute();
        verify(command, only()).execute();
    }
}

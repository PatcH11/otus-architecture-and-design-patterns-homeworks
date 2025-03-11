package ru.evsyukov.otus.exceptions.handlers.impl;

import org.junit.jupiter.api.Test;
import ru.evsyukov.otus.commands.Command;
import ru.evsyukov.otus.commands.impl.WriteToLogExceptionCommand;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class WriteToLogExceptionCommandHandlerTest {

    @Test
    public void givenException_whenApply_thenAddedToQueue() {
        // Given
        var exception = mock(RuntimeException.class);
        var queue = spy(new LinkedList<Command>());
        var handler = spy(new WriteToLogExceptionCommandHandler(queue, exception));

        assertTrue(queue.isEmpty());

        // When
        handler.execute();

        // Then
        assertFalse(queue.isEmpty());
        verify(queue, only()).add(any(WriteToLogExceptionCommand.class));
    }
}

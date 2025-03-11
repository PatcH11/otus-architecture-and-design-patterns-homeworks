package ru.evsyukov.otus.commands.impl;

import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

public class WriteToLogExceptionCommandTest {

    private LogCaptor logCaptor;

    @BeforeEach
    public void setUp() {
        logCaptor = LogCaptor.forClass(WriteToLogExceptionCommand.class);
    }

    @Test
    public void givenCommand_whenExecute_thenLogException() {
        // Given
        var exception = mock(RuntimeException.class);
        var writeToLogCommand = spy(new WriteToLogExceptionCommand(exception));

        // When
        writeToLogCommand.execute();

        // Then
        assertTrue(logCaptor.getErrorLogs().stream().anyMatch(log -> log.contains("Исключение залогированно!")));
    }
}

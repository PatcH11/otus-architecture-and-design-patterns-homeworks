package ru.evsyukov.otus.commands.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.evsyukov.otus.commands.Command;

@Slf4j
@RequiredArgsConstructor
public class WriteToLogExceptionCommand implements Command {

    private final Exception exception;

    @Override
    public void execute() {
        log.error("Исключение залогированно!", exception);
    }
}

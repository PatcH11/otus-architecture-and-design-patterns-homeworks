package ru.evsyukov.otus.exceptions.handlers.impl;

import lombok.RequiredArgsConstructor;
import ru.evsyukov.otus.commands.Command;
import ru.evsyukov.otus.commands.impl.WriteToLogExceptionCommand;

import java.util.Queue;

@RequiredArgsConstructor
public class WriteToLogExceptionCommandHandler implements Command {

    private final Queue<Command> commands;
    private final Exception exception;

    @Override
    public void execute() {
        var writeToLogExceptionCommand = new WriteToLogExceptionCommand(exception);
        commands.add(writeToLogExceptionCommand);
    }
}

package ru.evsyukov.otus.exceptions.handlers.impl;

import lombok.RequiredArgsConstructor;
import ru.evsyukov.otus.commands.Command;
import ru.evsyukov.otus.commands.impl.RetryCommand;

import java.util.Queue;

@RequiredArgsConstructor
public class RetryCommandHandler implements Command {

    private final Queue<Command> commands;
    private final Command command;

    @Override
    public void execute() {
        var retryCommand = new RetryCommand(command);
        commands.add(retryCommand);
    }
}

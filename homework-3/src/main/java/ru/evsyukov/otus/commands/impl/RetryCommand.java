package ru.evsyukov.otus.commands.impl;

import lombok.RequiredArgsConstructor;
import ru.evsyukov.otus.commands.Command;

@RequiredArgsConstructor
public class RetryCommand implements Command {

    private final Command command;

    @Override
    public void execute() {
        command.execute();
    }
}

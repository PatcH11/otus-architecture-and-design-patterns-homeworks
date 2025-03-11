package ru.evsyukov.otus.commands.impl;

import lombok.RequiredArgsConstructor;
import ru.evsyukov.otus.commands.Command;

@RequiredArgsConstructor
public class SecondRetryCommand implements Command {

    private final Command command;

    @Override
    public void execute() {
        command.execute();
    }
}

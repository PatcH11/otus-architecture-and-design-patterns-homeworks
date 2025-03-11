package ru.evsyukov.otus;

import lombok.RequiredArgsConstructor;
import ru.evsyukov.otus.commands.Command;
import ru.evsyukov.otus.exceptions.handlers.ExceptionHandler;

import java.util.Queue;

@RequiredArgsConstructor
public class EventLoop {

    private final Queue<Command> commands;
    private final ExceptionHandler exceptionHandler;

    public void update() {
        while (!commands.isEmpty()) {
            Command command = commands.poll();

            try {
                command.execute();
            } catch (Exception e) {
                exceptionHandler.getHandler(command, e).execute();
            }
        }
    }
}

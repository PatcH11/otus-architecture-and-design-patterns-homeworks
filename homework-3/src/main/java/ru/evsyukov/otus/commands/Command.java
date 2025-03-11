package ru.evsyukov.otus.commands;

/**
 * @author Nikolay Evsyukov
 */
@FunctionalInterface
public interface Command {

    /**
     *
     */
    void execute();
}

package ru.evsyukov.otus.exceptions.handlers;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import ru.evsyukov.otus.commands.Command;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

@Slf4j
public class ExceptionHandler {

    private static final Map<
            Class<? extends Command>,
            Map<
                    Class<? extends Exception>,
                    BiFunction<Command, Exception, Command>
                    >
            > STORE = new HashMap<>();

    @Setter
    public BiFunction<Command, Exception, Command> defaultCommandHandlerGenerator = (c, e) -> () -> log.info("Команда {} выкинула исключение {}", c.getClass(), e.getMessage());

    public void registerHandler(
            Class<? extends Command> commandClass,
            Class<? extends Exception> exceptionClass,
            BiFunction<Command, Exception, Command> commandGenerator) {
        if (!STORE.containsKey(commandClass)) {
            STORE.put(commandClass, new HashMap<>());
        }

        STORE.get(commandClass)
                .put(exceptionClass, commandGenerator);
    }

    public Command getHandler(Command command, Exception exception) {
        Class<? extends Command> commandClass = command.getClass();
        Class<? extends Exception> exceptionClass = exception.getClass();

        return STORE.getOrDefault(commandClass, Collections.emptyMap())
                .getOrDefault(exceptionClass, defaultCommandHandlerGenerator)
                .apply(command, exception);
    }
}

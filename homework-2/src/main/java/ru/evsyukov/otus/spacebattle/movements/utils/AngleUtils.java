package ru.evsyukov.otus.spacebattle.movements.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import ru.evsyukov.otus.spacebattle.movements.model.Angle;

/**
 * Набор утилитарных методов, для работы с углами.
 *
 * @author Nikolay Evsyukov
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AngleUtils {

    /**
     * Складывает два заданных угла и возвращает новый угол, представляющий собой
     * сумму их значений в радианах.
     *
     * @param firstAngle  первый угол, не должен быть null
     * @param secondAngle второй угол, не должен быть null
     * @return новый угол с значением в радианах, являющимся суммой значений заданных углов
     * @throws NullPointerException если любой из углов равен null
     */
    public static Angle plus(@NonNull Angle firstAngle, @NonNull Angle secondAngle) {
        double newAngle = firstAngle.getRadian() + secondAngle.getRadian();
        return new Angle(newAngle);
    }
}

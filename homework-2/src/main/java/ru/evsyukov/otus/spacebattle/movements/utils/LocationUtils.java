package ru.evsyukov.otus.spacebattle.movements.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import ru.evsyukov.otus.spacebattle.movements.model.Location;

/**
 * Набор утилитарных методов, для работы с позициями.
 *
 * @author Nikolay Evsyukov
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class LocationUtils {

    /**
     * Складывает две заданные локации и возвращает новую локацию, представляющую собой
     * сумму их координат.
     *
     * @param firstLocation  первая локация, не должна быть null
     * @param secondLocation вторая локация, не должна быть null
     * @return новая локация с координатами, являющимися суммой координат заданных локаций
     * @throws NullPointerException если любая из локаций равна null
     */
    public static Location plus(@NonNull Location firstLocation, @NonNull Location secondLocation) {
        double newX = firstLocation.getX() + secondLocation.getX();
        double newY = firstLocation.getY() + secondLocation.getY();

        return new Location(newX, newY);
    }
}

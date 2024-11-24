package ru.evsyukov.otus.spacebattle.movements;

import lombok.AllArgsConstructor;
import ru.evsyukov.otus.spacebattle.movements.model.Location;
import ru.evsyukov.otus.spacebattle.movements.model.MovingObject;
import ru.evsyukov.otus.spacebattle.movements.utils.LocationUtils;

/**
 * Реализация "прямолинейного движения".
 *
 * @author Nikolay Evsyukov
 */
@AllArgsConstructor
public class Move {

    private final MovingObject movingObject;

    public void execute() {
        Location location = movingObject.getLocation();
        Location velocity = movingObject.getVelocity();

        Location newLocation = LocationUtils.plus(location, velocity);

        movingObject.setLocation(newLocation);
    }
}

package ru.evsyukov.otus.spacebattle.movements.model;

import lombok.AllArgsConstructor;

/**
 * Примитивная реализация {@link MovingObject} для теста.
 *
 * @author Nikolay Evsyukov
 */
@AllArgsConstructor
public class SimpleMovingObject implements MovingObject {

    private Location location;
    private Location velocity;

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public void setLocation(Location newLocation) {
        this.location = newLocation;
    }

    @Override
    public Location getVelocity() {
        return velocity;
    }
}

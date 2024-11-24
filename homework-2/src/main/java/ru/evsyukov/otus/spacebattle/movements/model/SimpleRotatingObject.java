package ru.evsyukov.otus.spacebattle.movements.model;

import lombok.AllArgsConstructor;

/**
 * Примитивная реализация {@link RotatingObject} для теста.
 *
 * @author Nikolay Evsyukov
 */
@AllArgsConstructor
public class SimpleRotatingObject implements RotatingObject {

    private Angle angle;
    private Angle angularVelocity;

    @Override
    public Angle getAngle() {
        return angle;
    }

    @Override
    public void setAngle(Angle newAngle) {
        this.angle = newAngle;
    }

    @Override
    public Angle getAngularVelocity() {
        return angularVelocity;
    }
}

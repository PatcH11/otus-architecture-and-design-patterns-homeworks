package ru.evsyukov.otus.spacebattle.movements;

import lombok.AllArgsConstructor;
import ru.evsyukov.otus.spacebattle.movements.model.Angle;
import ru.evsyukov.otus.spacebattle.movements.model.RotatingObject;
import ru.evsyukov.otus.spacebattle.movements.utils.AngleUtils;

/**
 * Реализация "поворота".
 *
 * @author Nikolay Evsyukov
 */
@AllArgsConstructor
public class Rotate {

    private final RotatingObject rotatingObject;

    public void execute() {
        Angle angle = rotatingObject.getAngle();
        Angle angularVelocity = rotatingObject.getAngularVelocity();

        Angle newAngle = AngleUtils.plus(angle, angularVelocity);

        rotatingObject.setAngle(newAngle);
    }
}

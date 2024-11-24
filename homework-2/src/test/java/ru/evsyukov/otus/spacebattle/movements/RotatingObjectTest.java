package ru.evsyukov.otus.spacebattle.movements;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;
import ru.evsyukov.otus.spacebattle.movements.model.Angle;
import ru.evsyukov.otus.spacebattle.movements.model.RotatingObject;
import ru.evsyukov.otus.spacebattle.movements.model.SimpleRotatingObject;

/**
 * Тестовый класс для проверки функциональности "поворота".
 *
 * @author Nikolay Evsyukov
 */
public class RotatingObjectTest {

    private static final double DELTA = 0.0001;

    @Test
    @DisplayName("Тест, который проверяет, что для объекта, имеющего угол 12 и имеющего угловую скорость 2 при повороте он будет иметь угол 14")
    public void testRotateUpdatesLocationCorrectly() {
        Angle angle = Mockito.spy(new Angle(12));
        Angle angularVelocity = Mockito.spy(new Angle(2));

        RotatingObject rotatingObject = Mockito.spy(new SimpleRotatingObject(angle, angularVelocity));

        Rotate rotate = Mockito.spy(new Rotate(rotatingObject));
        rotate.execute();

        Assertions.assertEquals(14, rotatingObject.getAngle().getRadian(), DELTA);
    }

    @Test
    @DisplayName("Тест, который проверяет, что попытка повернуть объект, у которого невозможно прочитать текущее значение угла, приводит к ошибке")
    public void testRotateThrowsExceptionWhenAngleUnreadable() {
        RotatingObject rotatingObject = Mockito.mock(RotatingObject.class);
        Mockito.when(rotatingObject.getAngle()).thenThrow(IllegalStateException.class);

        Rotate rotate = Mockito.spy(new Rotate(rotatingObject));

        Assertions.assertThrows(IllegalStateException.class, rotate::execute);
    }

    @Test
    @DisplayName("Тест, который проверяет, что попытка повернуть объект, у которого невозможно прочитать текущую угловую скорость, приводит к ошибке")
    public void testRotateThrowsExceptionWhenAngularVelocityUnreadable() {
        RotatingObject rotatingObject = Mockito.mock(RotatingObject.class);
        Mockito.when(rotatingObject.getAngularVelocity()).thenThrow(IllegalStateException.class);

        Rotate rotate = Mockito.spy(new Rotate(rotatingObject));

        Assertions.assertThrows(IllegalStateException.class, rotate::execute);
    }

    @Test
    @DisplayName("Тест, который проверяет, что попытка повернуть объект, у которого невозможно изменить угол, приводит к ошибке")
    public void testRotateThrowsExceptionWhenAngleUnmodifiable() {
        Angle angle = Mockito.spy(new Angle(12));
        Angle angularVelocity = Mockito.spy(new Angle(2));

        RotatingObject rotatingObject = Mockito.mock(RotatingObject.class);
        Mockito.when(rotatingObject.getAngle()).thenReturn(angle);
        Mockito.when(rotatingObject.getAngularVelocity()).thenReturn(angularVelocity);
        Mockito.doThrow(IllegalStateException.class).when(rotatingObject).setAngle(Mockito.any());

        Rotate rotate = Mockito.spy(new Rotate(rotatingObject));

        Assertions.assertThrows(IllegalStateException.class, rotate::execute);
    }
}

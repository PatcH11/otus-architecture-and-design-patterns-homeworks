package ru.evsyukov.otus.spacebattle.movements;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;
import ru.evsyukov.otus.spacebattle.movements.model.Location;
import ru.evsyukov.otus.spacebattle.movements.model.MovingObject;
import ru.evsyukov.otus.spacebattle.movements.model.SimpleMovingObject;

/**
 * Тестовый класс для проверки функциональности "прямолинейного движения".
 *
 * @author Nikolay Evsyukov
 */
public class MovingObjectTest {

    private static final double DELTA = 0.0001;

    @Test
    @DisplayName("Тест, который проверяет, что для объекта, находящегося в точке (12, 5) и движущегося со скоростью (-7, 3) движение меняет положение объекта на (5, 8)")
    public void testMoveUpdatesLocationCorrectly() {
        Location location = Mockito.spy(new Location(12, 5));
        Location velocity = Mockito.spy(new Location(-7, 3));

        MovingObject movingObject = Mockito.spy(new SimpleMovingObject(location, velocity));

        Move move = Mockito.spy(new Move(movingObject));
        move.execute();

        Assertions.assertEquals(5, movingObject.getLocation().getX(), DELTA);
        Assertions.assertEquals(8, movingObject.getLocation().getY(), DELTA);
    }

    @Test
    @DisplayName("Тест, который проверяет, что попытка сдвинуть объект, у которого невозможно прочитать положение в пространстве, приводит к ошибке")
    public void testMoveThrowsExceptionWhenLocationUnreadable() {
        MovingObject movingObject = Mockito.mock(MovingObject.class);
        Mockito.when(movingObject.getLocation()).thenThrow(IllegalStateException.class);

        Move move = Mockito.spy(new Move(movingObject));

        Assertions.assertThrows(IllegalStateException.class, move::execute);
    }

    @Test
    @DisplayName("Тест, который проверяет, что попытка сдвинуть объект, у которого невозможно прочитать значение мгновенной скорости, приводит к ошибке")
    public void testMoveThrowsExceptionWhenVelocityUnreadable() {
        MovingObject movingObject = Mockito.mock(MovingObject.class);
        Mockito.when(movingObject.getVelocity()).thenThrow(IllegalStateException.class);

        Move move = Mockito.spy(new Move(movingObject));

        Assertions.assertThrows(IllegalStateException.class, move::execute);
    }

    @Test
    @DisplayName("Тест, который проверяет, что попытка сдвинуть объект, у которого невозможно изменить положение в пространстве, приводит к ошибке")
    public void testMoveThrowsExceptionWhenLocationUnmodifiable() {
        Location location = Mockito.spy(new Location(12, 5));
        Location velocity = Mockito.spy(new Location(-7, 3));

        MovingObject movingObject = Mockito.mock(MovingObject.class);
        Mockito.when(movingObject.getLocation()).thenReturn(location);
        Mockito.when(movingObject.getVelocity()).thenReturn(velocity);
        Mockito.doThrow(IllegalStateException.class).when(movingObject).setLocation(Mockito.any());

        Move move = Mockito.spy(new Move(movingObject));

        Assertions.assertThrows(IllegalStateException.class, move::execute);
    }
}

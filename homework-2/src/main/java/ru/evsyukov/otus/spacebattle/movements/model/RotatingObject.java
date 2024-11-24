package ru.evsyukov.otus.spacebattle.movements.model;

/**
 * Интерфейс, представляющий поворачивающися объект в пространстве.
 *
 * @author Nikolay Evsyukov
 */
public interface RotatingObject {

    /**
     * Возвращает текущий угол объекта.
     *
     * @return текущий угол объекта
     */
    Angle getAngle();

    /**
     * Устанавливает новый угол для объекта.
     *
     * @param newAngle новый угол объекта
     */
    void setAngle(Angle newAngle);

    /**
     * Возвращает текущую угловую скорость объекта.
     *
     * @return текущая угловая скорость объекта
     */
    Angle getAngularVelocity();
}

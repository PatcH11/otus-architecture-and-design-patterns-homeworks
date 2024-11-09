package ru.evsyukov.otus.spacebattle.movements.model;

/**
 * Интерфейс, представляющий движущийся объект в пространстве.
 *
 * @author Nikolay Evsyukov
 */
public interface MovingObject {

    /**
     * Возвращает текущее местоположение объекта.
     *
     * @return текущая локация объекта
     */
    Location getLocation();

    /**
     * Устанавливает новое местоположение для объекта.
     *
     * @param newLocation новая локация объекта
     */
    void setLocation(Location newLocation);

    /**
     * Возвращает текущую скорость объекта.
     *
     * @return текущая скорость объекта
     */
    Location getVelocity();
}

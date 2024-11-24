package ru.evsyukov.otus.spacebattle.movements.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Представляет позицию в геометрическом контексте.
 *
 * @author Nikolay Evsyukov
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Location {

    private double x;
    private double y;
}

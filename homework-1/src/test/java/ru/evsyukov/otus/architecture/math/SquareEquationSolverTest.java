package ru.evsyukov.otus.architecture.math;

import lombok.Value;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.stream.Stream;

/**
 * Тестовый класс для проверки функциональности решения квадратных уравнений
 * с использованием класса {@link SquareEquationSolver}.
 *
 * @author Nikolay Evsyukov
 */
public class SquareEquationSolverTest {

    private static final double EPSILON = 0.001;

    @Test
    @DisplayName("Тест, который проверяет, что для уравнения x^2+1 = 0 корней нет (возвращается пустой массив)")
    public void testNoRealRootsForEquation() {
        double[] answer = SquareEquationSolver.solve(1, 0, 1, EPSILON);
        Assertions.assertArrayEquals(new double[]{}, answer);
    }

    @Test
    @DisplayName("Тест, который проверяет, что для уравнения x^2-1 = 0 есть два корня кратности 1 (x1=1, x2=-1)")
    public void testTwoDistinctRootsForEquation() {
        double[] answer = SquareEquationSolver.solve(1, 0, -1, EPSILON);
        Assertions.assertArrayEquals(new double[]{1, -1}, answer);
    }

    @Test
    @DisplayName("Тест, который проверяет, что для уравнения x^2+2x+1 = 0 есть один корень кратности 2 (x1= x2 = -1)")
    public void testSingleRootMultiplicityTwoForEquation() {
        double[] answer = SquareEquationSolver.solve(1, 2, 1, EPSILON);
        Assertions.assertArrayEquals(new double[]{-1, -1}, answer);
    }

    @Test
    @DisplayName("Тест, который проверяет, что коэффициент a не может быть равен 0")
    public void testCoefficientANotZero() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SquareEquationSolver.solve(0, 2, 1, EPSILON);
        });
    }

    @Test
    @DisplayName("Тест, который проверяет работу, когда дискриминант больше 0, но меньше EPSILON")
    public void testDiscriminantGreaterThanZeroAndLessThanEpsilon() {
        double[] answer = SquareEquationSolver.solve(1, 2.000125, 1, EPSILON);
        Assertions.assertArrayEquals(new double[]{-1.0000625, -1.0000625}, answer);
    }

    @ParameterizedTest
    @ArgumentsSource(CoefficientsArgumentsProvider.class)
    @DisplayName("Тест, который проверяет, что коэффициенты не могут быть не числовыми")
    public void testCoefficientsCannotBeNonNumeric(Coefficients coefficients) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SquareEquationSolver.solve(coefficients.a, coefficients.b, coefficients.c, coefficients.e);
        });
    }

    private static class CoefficientsArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(new Coefficients(Double.NaN, 1, 1, EPSILON)),
                    Arguments.of(new Coefficients(1, Double.NaN, 1, EPSILON)),
                    Arguments.of(new Coefficients(1, 1, Double.NaN, EPSILON)),
                    Arguments.of(new Coefficients(1, 1, 1, Double.NaN)),
                    Arguments.of(new Coefficients(Double.NaN, Double.NaN, Double.NaN, Double.NaN)),
                    Arguments.of(new Coefficients(Double.NEGATIVE_INFINITY, 1, 1, EPSILON)),
                    Arguments.of(new Coefficients(1, Double.NEGATIVE_INFINITY, 1, EPSILON)),
                    Arguments.of(new Coefficients(1, 1, Double.NEGATIVE_INFINITY, EPSILON)),
                    Arguments.of(new Coefficients(1, 1, 1, Double.NEGATIVE_INFINITY)),
                    Arguments.of(new Coefficients(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY)),
                    Arguments.of(new Coefficients(Double.POSITIVE_INFINITY, 1, 1, EPSILON)),
                    Arguments.of(new Coefficients(1, Double.POSITIVE_INFINITY, 1, EPSILON)),
                    Arguments.of(new Coefficients(1, 1, Double.POSITIVE_INFINITY, EPSILON)),
                    Arguments.of(new Coefficients(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY))
            );
        }
    }

    @Value
    private static class Coefficients {
        double a;
        double b;
        double c;
        double e;
    }
}
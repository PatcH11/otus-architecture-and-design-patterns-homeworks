package ru.evsyukov.otus.architecture.math;

/**
 * Класс для решения квадратного уравнения вида ax^2 + bx + c = 0.
 *
 * <p>Этот класс предоставляет статический метод для нахождения корней
 * квадратного уравнения, принимая во внимание допустимую погрешность (epsilon)
 * при вычислении дискриминанта. Класс является утилитарным и не может быть
 * инстанцирован.</p>
 *
 * <p>Результаты вычислений зависят от значений коэффициентов и погрешности:</p>
 * <ul>
 *     <li>Если дискриминант меньше -epsilon, уравнение не имеет действительных корней.</li>
 *     <li>Если дискриминант находится в пределах от -epsilon до epsilon, считается, что уравнение имеет два одинаковых корня (один корень с учетом погрешности).</li>
 *     <li>Если дискриминант больше epsilon, уравнение имеет два различных действительных корня.</li>
 * </ul>
 *
 * <p>Методы проверки гарантируют, что переданные коэффициенты и значение epsilon
 * являются допустимыми для корректного выполнения вычислений.</p>
 *
 * @author Nikolay Evsyukov
 */
public final class SquareEquationSolver {

    private SquareEquationSolver() {
    }

    /**
     * Решает квадратное уравнение вида ax^2 + bx + c = 0 с учетом заданной погрешности epsilon.
     *
     * @param a коэффициент при x^2
     * @param b коэффициент при x
     * @param c свободный член
     * @param e допустимая погрешность для вычисления дискриминанта
     * @return массив действительных корней уравнения. Если корней нет, возвращается пустой массив.
     * @throws IllegalArgumentException если переданы недопустимые значения коэффициентов или epsilon
     */
    public static double[] solve(double a, double b, double c, double e) {
        checkValidDouble(a, b, c, e);
        checkValidEpsilon(e);
        checkValidAArgument(a, e);

        double discriminant = calculateDiscriminant(a, b, c);

        if (discriminant < -e) {
            // D < 0: коней нет
            return new double[]{};
        } else if (Math.abs(discriminant) <= e) {
            // D = 0: два одинаковых корня
            double single = calculateSingleSquareRootOfEquation(a, b);
            return new double[]{single, single};
        } else {
            // D > 0: два разных корня
            double first = calculateFirstSquareRootOfEquation(a, b, discriminant);
            double second = calculateSecondSquareRootOfEquation(a, b, discriminant);
            return new double[]{first, second};
        }
    }

    private static void checkValidDouble(double... args) {
        for (var arg : args) {
            if (Double.isNaN(arg) || Double.isInfinite(arg)) {
                throw new IllegalArgumentException();
            }
        }
    }

    private static void checkValidEpsilon(double e) {
        if (e <= 0) {
            throw new IllegalArgumentException();
        }
    }

    private static void checkValidAArgument(double a, double e) {
        if (Math.abs(a) < e) {
            throw new IllegalArgumentException();
        }
    }

    private static double calculateDiscriminant(double a, double b, double c) {
        return b * b - 4 * a * c;
    }

    private static double calculateFirstSquareRootOfEquation(double a, double b, double d) {
        return (-b + Math.sqrt(d)) / (2 * a);
    }

    private static double calculateSecondSquareRootOfEquation(double a, double b, double d) {
        return (-b - Math.sqrt(d)) / (2 * a);
    }

    private static double calculateSingleSquareRootOfEquation(double a, double b) {
        return -b / (2 * a);
    }
}

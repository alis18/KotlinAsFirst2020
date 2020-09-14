@file:Suppress("UNUSED_PARAMETER")

package lesson2.task2

import lesson1.task1.sqr
import kotlin.math.abs
import kotlin.math.min
import kotlin.math.sqrt

/**
 * Пример
 *
 * Лежит ли точка (x, y) внутри окружности с центром в (x0, y0) и радиусом r?
 */
fun pointInsideCircle(x: Double, y: Double, x0: Double, y0: Double, r: Double) =
    sqr(x - x0) + sqr(y - y0) <= sqr(r)

/**
 * Простая (2 балла)
 *
 * Четырехзначное число назовем счастливым, если сумма первых двух ее цифр равна сумме двух последних.
 * Определить, счастливое ли заданное число, вернуть true, если это так.
 */
fun isNumberHappy(number: Int): Boolean =
    number / 1000 + number / 100 % 10 == number % 100 / 10 + number % 10

/**
 * Простая (2 балла)
 *
 * На шахматной доске стоят два ферзя (ферзь бьет по вертикали, горизонтали и диагоналям).
 * Определить, угрожают ли они друг другу. Вернуть true, если угрожают.
 * Считать, что ферзи не могут загораживать друг друга.
 */
fun queenThreatens(x1: Int, y1: Int, x2: Int, y2: Int): Boolean =
    (x1 == x2) || (y1 == y2) || (abs(x1 - x2) == abs(y1 - y2))


/**
 * Простая (2 балла)
 *
 * Дан номер месяца (от 1 до 12 включительно) и год (положительный).
 * Вернуть число дней в этом месяце этого года по григорианскому календарю.
 */
fun daysInMonth(month: Int, year: Int): Int {
    var ans = 0
    if ((month == 1) || (month == 3) || (month == 5) || (month == 7) || (month == 8) || (month == 10) || (month == 12)) ans =
        31
    else if ((month == 4) || (month == 6) || (month == 9) || (month == 11)) ans = 30
    else if (((month == 2) && (year % 4 == 0)) && (year % 100 != 0) || (year % 400 == 0)) ans = 29
    else ans = 28
    return ans
}

/**
 * Простая (2 балла)
 *
 * Проверить, лежит ли окружность с центром в (x1, y1) и радиусом r1 целиком внутри
 * окружности с центром в (x2, y2) и радиусом r2.
 * Вернуть true, если утверждение верно
 */
fun circleInside(
    x1: Double, y1: Double, r1: Double,
    x2: Double, y2: Double, r2: Double
): Boolean =
    r2 >= r1 + sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2))


/**
 * Средняя (3 балла)
 *
 * Определить, пройдет ли кирпич со сторонами а, b, c сквозь прямоугольное отверстие в стене со сторонами r и s.
 * Стороны отверстия должны быть параллельны граням кирпича.
 * Считать, что совпадения длин сторон достаточно для прохождения кирпича, т.е., например,
 * кирпич 4 х 4 х 4 пройдёт через отверстие 4 х 4.
 * Вернуть true, если кирпич пройдёт
 */
fun brickPasses(a: Int, b: Int, c: Int, r: Int, s: Int): Boolean {
    var min1 = 0
    var min2 = 0
    var min3 = 0
    min1 = minOf(a, b, c)
    if (a == min1) {
        if (b < c) {
            min2 = b
            min3 = c
        } else {
            min2 = c
            min3 = b
        }
    }
    if (b == min1) {
        if (a < c) {
            min2 = a
            min3 = c
        } else {
            min2 = c
            min3 = a
        }
    }
    if (c == min1) {
        if (b < a) {
            min2 = b
            min3 = a
        } else {
            min2 = a
            min3 = b
        }
    }
    return (min1 * min2 <= r * s) && ((min1 <= r) && (min2 <= s) || (min1 <= s) && (min2 <= r))
}

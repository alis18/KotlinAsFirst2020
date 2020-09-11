@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import kotlin.math.max
import kotlin.math.sqrt
import kotlin.math.abs

// Урок 2: ветвления (здесь), логический тип (см. 2.2).
// Максимальное количество баллов = 6
// Рекомендуемое количество баллов = 5
// Вместе с предыдущими уроками = 9/12

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая (2 балла)
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String {
    var word = ""
    if (age % 100 in 5..20) word = "лет" else
        if (age % 10 == 1) word = "год" else
            if (age % 10 in 2..4) word = "года" else
                if ((age % 10 in 5..9) || (age % 10 == 0)) word = "лет"
    return "$age $word"
}

/**
 * Простая (2 балла)
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(
    t1: Double, v1: Double,
    t2: Double, v2: Double,
    t3: Double, v3: Double
): Double {
    val s = v1 * t1 + v2 * t2 + v3 * t3
    var t = 0.0
    if (s / 2 < v1 * t1) t = s / 2 / v1
    if (s / 2 in v1 * t1..v1 * t1 + v2 * t2) t = t1 + (s / 2 - v1 * t1) / v2
    if (s / 2 > v1 * t1 + v2 * t2) t = t1 + t2 + (s / 2 - v1 * t1 - v2 * t2) / v3
    return t
}

/**
 * Простая (2 балла)
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(
    kingX: Int, kingY: Int,
    rookX1: Int, rookY1: Int,
    rookX2: Int, rookY2: Int
): Int {
    var a = 0
    var b = 0
    if ((kingX == rookX1) || (kingY == rookY1)) a = 1
    if ((kingX == rookX2) || (kingY == rookY2)) b = 2
    return a + b
}

/**
 * Простая (2 балла)
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(
    kingX: Int, kingY: Int,
    rookX: Int, rookY: Int,
    bishopX: Int, bishopY: Int
): Int {
    var a = 0
    var b = 0
    if ((kingX == rookX) || (kingY == rookY)) a = 1
    if (abs(kingX - bishopX) == abs(kingY - bishopY)) b = 2
    return a + b
}

/**
 * Простая (2 балла)
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    var max1 = 0.0
    var max2 = 0.0
    var max3 = 0.0
    var ans = 0
    max1 = max(max(a, b), max(b, c))
    if (a == max1) if (b > c) {
        max2 = b
        max3 = c
    } else {
        max2 = c
        max3 = b
    }
    if (b == max1) if (a > c) {
        max2 = a
        max3 = c
    } else {
        max2 = c
        max3 = a
    }
    if (c == max1) if (b > a) {
        max2 = b
        max3 = a
    } else {
        max2 = a
        max3 = b
    }
    if (max1 > max2 + max3) ans = -1 else {
        if (sqr(max1) == sqr(max2) + sqr(max3)) ans = 1
        if (sqr(max1) < sqr(max2) + sqr(max3)) ans = 0
        if (sqr(max1) > sqr(max2) + sqr(max3)) ans = 2
    }
    return ans
}

/**
 * Средняя (3 балла)
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int {
    var start1 = 0
    var end1 = 0
    var start2 = 0
    var end2 = 0
    if (a < c) {
        start1 = a
        end1 = b
        start2 = c
        end2 = d
    } else {
        start1 = c
        end1 = d
        start2 = a
        end2 = b
    }
    var ans = 0

    if (end1 > end2) ans = end2 - start2 else if (start2 < end1) ans = end1 - start2 else if (start2 == end1) ans =
        0 else ans = -1

    return ans
}

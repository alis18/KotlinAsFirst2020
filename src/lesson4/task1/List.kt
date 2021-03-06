@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson3.task1.digitNumber
import kotlin.math.sqrt
import kotlin.math.pow

// Урок 4: списки
// Максимальное количество баллов = 12
// Рекомендуемое количество баллов = 8
// Вместе с предыдущими уроками = 24/33

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая (2 балла)
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    var ans = 0.0
    for (i in 0..v.size - 1) {
        ans += v[i] * v[i]
    }
    return sqrt(ans)
}

/**
 * Простая (2 балла)
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double = TODO()

/**
 * Средняя (3 балла)
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> = TODO()

/**
 * Средняя (3 балла)
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int = TODO()

/**
 * Средняя (3 балла)
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int = TODO()

/**
 * Средняя (3 балла)
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> = TODO()

/**
 * Средняя (3 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> = TODO()

/**
 * Сложная (4 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = TODO()

/**
 * Средняя (3 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> = TODO()

/**
 * Сложная (4 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String = TODO()

/**
 * Средняя (3 балла)
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int = TODO()

/**
 * Сложная (4 балла)
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int = TODO()

/**
 * Сложная (5 баллов)
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    val const = listOf<Char>('I', 'V', 'X', 'L', 'C', 'D', 'M')
    var string = StringBuilder()
    var number = n
    var lengthOfNumber = digitNumber(number)
    while (lengthOfNumber > 0) {
        val x = number / 10.0.pow(lengthOfNumber-1.toDouble()).toInt()
        number %= 10.0.pow(lengthOfNumber - 1.toDouble()).toInt()
        var i = 0
        when (lengthOfNumber) {
            4 -> i = 7
            3 -> i = 5
            2 -> i = 3
            1 -> i = 1
        }
        when (x) {
            1 -> string.append(const[i - 1])
            2 -> string.append(const[i - 1],const[i - 1])
            3 -> string.append(const[i - 1],const[i - 1],const[i - 1])
            4 -> string.append(const[i - 1],const[i])
            5 -> string.append(const[i])
            6 -> string.append(const[i],const[i - 1])
            7 -> string.append(const[i],const[i - 1],const[i - 1])
            8 -> string.append(const[i],const[i - 1],const[i - 1],const[i - 1])
            9 -> string.append(const[i - 1],const[i + 1])
            else -> break
        }
        lengthOfNumber = digitNumber(number)
    }
    return string.toString()
}

/**
 * Очень сложная (7 баллов)
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    var ans = mutableListOf<String>()
    var number = n
    var step = 10.0.pow((digitNumber(number)-1).toDouble()).toInt()
    var lengthOfNumber = digitNumber(number)
    while (lengthOfNumber > 0) {
        var x = number / step
        var y = 0
        if (lengthOfNumber != 1) y = number / (step / 10) % 10
        number = number % step
        step /= 10
        var i = 0
        when (lengthOfNumber % 3) {
            0 -> i = 0
            1 -> i = 1
            2 -> i = 2
        }
        if (i == 1) when (x) {
            1 -> {
                if (lengthOfNumber == 1) ans.add("один") else ans.add("одна")
            }
            2 -> {
                if (lengthOfNumber == 1) ans.add("два") else ans.add("две")
            }
            3 -> ans.add("три")
            4 -> ans.add("четыре")
            5 -> ans.add("пять")
            6 -> ans.add("шесть")
            7 -> ans.add("семь")
            8 -> ans.add("восемь")
            9 -> ans.add("девять")
            else -> ""
        }
        if (i == 2) when (x) {
            1 -> {
                when (y) {
                    0 -> ans.add("десять")
                    1 -> ans.add("одиннадцать")
                    2 -> ans.add("двенадцать")
                    3 -> ans.add("тринадцать")
                    4 -> ans.add("четырнадцать")
                    5 -> ans.add("пятнадцать")
                    6 -> ans.add("шестнадцать")
                    7 -> ans.add("семнадцать")
                    8 -> ans.add("восемнадцать")
                    9 -> ans.add("девятнадцать")
                }
                lengthOfNumber--
                x = number / step
                number %= step
                step /= 10
            }
            2 -> ans.add("двадцать")
            3 -> ans.add("тридцать")
            4 -> ans.add("сорок")
            5 -> ans.add("пятьдесят")
            6 -> ans.add("шестьдесят")
            7 -> ans.add("семьдесят")
            8 -> ans.add("восемьдесят")
            9 -> ans.add("девяносто")
            else -> ""
        }
        if (i == 0) when (x) {
            1 -> ans.add("сто")
            2 -> ans.add("двести")
            3 -> ans.add("триста")
            4 -> ans.add("четыреста")
            5 -> ans.add("пятьсот")
            6 -> ans.add("шестьсот")
            7 -> ans.add("семьсот")
            8 -> ans.add("восемьсот")
            9 -> ans.add("девятьсот")
            else -> ""
        }
        lengthOfNumber--
        if (lengthOfNumber == 3) {
            if (x == 1 && i != 2) ans.add("тысяча")
            else if (x < 5 && x != 0 && i != 2) ans.add("тысячи")
            else ans.add("тысяч")
        }
    }
    var ansFinl = ""
    ansFinl = ans.joinToString(separator = " ")
    return ansFinl
}
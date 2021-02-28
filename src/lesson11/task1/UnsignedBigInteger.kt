package lesson11.task1

import kotlin.math.min
import kotlin.math.max
import kotlin.math.pow

/**
 * Класс "беззнаковое большое целое число".
 *
 * Общая сложность задания -- очень сложная, общая ценность в баллах -- 32.
 * Объект класса содержит целое число без знака произвольного размера
 * и поддерживает основные операции над такими числами, а именно:
 * сложение, вычитание (при вычитании большего числа из меньшего бросается исключение),
 * умножение, деление, остаток от деления,
 * преобразование в строку/из строки, преобразование в целое/из целого,
 * сравнение на равенство и неравенство
 */
class UnsignedBigInteger : Comparable<UnsignedBigInteger> {

    var data = mutableListOf<Int>()

    /**
     * Конструктор из строки
     */
    constructor(s: String) {
        var str = s
        if (s.matches(Regex("""0+"""))) data.add(0) else {
            while (str[0] == '0') str = str.removeRange(0, 1)
            for (i in str.length - 1 downTo 0) {
                data.add(s[i].toString().toInt())
            }
        }
    }

    /**
     * Конструктор из целого
     */
    constructor(i: Int) {
        val s = i.toString()
        val len = s.length
        for (j in len - 1 downTo 0) {
            data.add(s[j].toString().toInt())
        }
    }

    constructor(list: MutableList<Int>) {
        val newlist = list.toMutableList()
        val ans = newlist.dropLastWhile { it == 0 }.toMutableList()
        if (ans.isEmpty()) ans += 0
        data.addAll(ans)
    }

    /**
     * Сложение
     */
    operator fun plus(other: UnsignedBigInteger): UnsignedBigInteger {
        val maximum = max(data.size, other.data.size)
        val minimum = min(data.size, other.data.size)
        val ans = mutableListOf<Int>()
        var ost = 0
        for (i in 0 until minimum) {
            ans.add((data[i] + other.data[i] + ost) % 10)
            ost = (data[i] + other.data[i] + ost) / 10
        }
        if (ost == 1 && minimum == maximum) {
            ans.add(1)
            ost = 0
        }
        val big = if (this > other) this else other
        for (i in minimum until maximum) {
            ans.add((big.data[i] + ost) % 10)
            ost = (big.data[i] + ost) / 10
        }
        if (ost == 1) ans.add(1)
        return UnsignedBigInteger(ans)
    }

    /**
     * Вычитание (бросить ArithmeticException, если this < other)
     */
    operator fun minus(other: UnsignedBigInteger): UnsignedBigInteger {
        val ans = mutableListOf<Int>()
        if (other > this) throw ArithmeticException("this < other")
        if (other == this) return UnsignedBigInteger(0)
        val minimum = min(other.data.size, data.size)
        val maximum = max(other.data.size, data.size)
        val dataa = this.data.toMutableList()
        for (i in 0 until minimum) {
            if (dataa[i] >= other.data[i]) {
                ans.add(dataa[i] - other.data[i])
            } else {
                dataa[i + 1] -= 1
                dataa[i] += 10
                ans.add(dataa[i] - other.data[i])
            }
        }
        for (i in minimum until maximum) {
            if (dataa[i] == -1) {
                dataa[i + 1] -= 1
                dataa[i] += 10
                ans.add(dataa[i])
            } else ans.add((dataa[i]))
        }
        return UnsignedBigInteger(ans)
    }

    /**
     * Умножение
     */
    operator fun times(other: UnsignedBigInteger): UnsignedBigInteger {
        val ans = MutableList(max(data.size, other.data.size) * 2 + 1) { 0 }
        var step = 0
        var dopper = 0
        for (i in 0 until other.data.size) {
            for (j in 0 until data.size) {
                ans[step] = ans[step] + ((other.data[i] * data[j] + dopper) % 10)
                dopper = (other.data[i] * data[j] + dopper) / 10
                step++
            }
            if (dopper != 0) {
                ans[step] = ans[step] + dopper
                dopper = 0
            }
            step = i + 1
        }
        for (i in 0 until ans.size) {
            if (ans[i] >= 10) {
                ans[i + 1] = ans[i + 1] + (ans[i] / 10)
                ans[i] = ans[i] % 10
            }
        }
        return UnsignedBigInteger(ans)
    }

    /**
     * Деление
     */
    operator fun div(other: UnsignedBigInteger): UnsignedBigInteger {
        when {
            this < other -> return UnsignedBigInteger("0")
            this == other -> return UnsignedBigInteger("1")
            else -> {
                val ans = mutableListOf<Int>()
                var del = UnsignedBigInteger(0)
                var step = 0
                for (i in this.data.size - 1 downTo 0) {
                    del *= UnsignedBigInteger(10)
                    del += UnsignedBigInteger(this.data[i])
                    if (del >= other) {
                        while (del >= other) {
                            del -= other
                            step++
                        }
                        ans.add(0, step)
                        step = 0
                        var b = UnsignedBigInteger(0)
                        for (j in del.data.size - 1 downTo 0) {
                            b *= UnsignedBigInteger(10)
                            b += UnsignedBigInteger(del.data[j])
                        }
                        del = b
                    }
                }
                return UnsignedBigInteger(ans)
            }
        }
    }

    /**
     * Взятие остатка
     */
    operator fun rem(other: UnsignedBigInteger): UnsignedBigInteger = this - this / other * other

    /**
     * Сравнение на равенство (по контракту Any.equals)
     */
    override fun equals(other: Any?): Boolean =
        !((other !is UnsignedBigInteger) || (data != other.data))


    /**
     * Сравнение на больше/меньше (по контракту Comparable.compareTo)
     */
    override fun compareTo(other: UnsignedBigInteger): Int {
        when {
            data.size > other.data.size -> return 1
            data.size < other.data.size -> return -1
            else -> {
                for (i in data.size - 1 downTo 0) {
                    if (data[i] < other.data[i]) return -1
                    if (data[i] > other.data[i]) return 1
                }
                return 0
            }
        }
    }

    /**
     * Преобразование в строку
     */
    override fun toString(): String = buildString { for (i in data.size downTo 1) append(data[i - 1].toString()) }

    /**
     * Преобразование в целое
     * Если число не влезает в диапазон Int, бросить ArithmeticException
     */
    fun toInt(): Int {
        var ansint = 0
        val int = UnsignedBigInteger(Int.MAX_VALUE)
        if (this > int) throw ArithmeticException("more then int")
        else {
            for (i in data.size - 1 downTo 0) {
                ansint += data[i] * 10.0.pow((i).toDouble()).toInt()
            }
        }
        return ansint
    }

    override fun hashCode(): Int = data.hashCode()
}
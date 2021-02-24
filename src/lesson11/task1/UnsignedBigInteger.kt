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

    /**
     * Сложение
     */
    operator fun plus(other: UnsignedBigInteger): UnsignedBigInteger {
        val maximum = max(data.size, other.data.size)
        val minimum = min(data.size, other.data.size)
        val ans = mutableListOf<Int>()
        var ost = 0
        for (i in 0 until minimum) {
            ans.add((data[i] + other.data[i]) % 10 + ost)
            ost = (data[i] + other.data[i]) / 10
        }
        println(ans)
        if (ost == 1 && minimum == maximum) {
            ans.add(1)
            ost = 0
        }
        for (i in minimum until maximum) {
            if (ost == 0) {
                if ((data.size > other.data.size)) {
                    ans.add(data[i])
                }
                if ((data.size < other.data.size)) {
                    ans.add(other.data[i])
                }
            } else {

                if ((data.size > other.data.size)) {
                    if (data[i] + 1 > 9) {
                        ans.add((data[i] + 1) % 10)
                    } else {
                        ans.add(data[i] + 1)
                        ost = 0
                    }
                }
                if ((data.size < other.data.size)) {
                    if (other.data[i] + 1 > 9) {
                        ans.add((other.data[i] + 1) % 10)
                    } else {
                        ans.add(other.data[i] + 1)
                        ost = 0
                    }
                }

            }
        }
        if (ost == 1) ans.add(1)
        var ansstr = ""
        for (i in ans.size - 1 downTo 0) {
            ansstr = ans[ans.size - i - 1].toString() + ansstr
        }
        return UnsignedBigInteger(ansstr)
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

        for (i in 0 until minimum) {
            if (data[i] >= other.data[i]) {
                ans.add(data[i] - other.data[i])
            } else {
                data[i + 1] -= 1
                data[i] += 10
                ans.add(data[i] - other.data[i])
            }
        }
        for (i in minimum until maximum) {
            if (data[i] == -1) {
                data[i + 1] -= 1
                data[i] += 10
                ans.add(data[i])
            } else ans.add((data[i]))
        }

        var ansstr = ""
        for (i in ans.size - 1 downTo 0) {
            ansstr += ans[i].toString()
            if (ansstr.matches(Regex("""0"""))) ansstr = ""
        }
        return UnsignedBigInteger(ansstr)
    }

    /**
     * Умножение
     */
    operator fun times(other: UnsignedBigInteger): UnsignedBigInteger {
        //val ans = mutableMapOf<Int, Int>()
        val ans = MutableList(max(data.size, other.data.size) * 2 + 1) { 0 }
        //for (i in 0..max(data.size, other.data.size) * 2) ans[i] = 0
        //println(ans.size)
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
        var ansstr = ""
        var ost = 0
        for (i in (ans.size - 1) downTo 0) {
            if ((ans[i] == 0) && (ost == 0) && (ans[i - 1] != 0)) ost = 1 else
                if (ost == 1) {
                    ansstr += ans[i].toString()
                }
            if (i == 1 && ans[0] == 0 && ost == 0) {
                break
            }
        }
        if (ansstr == "") ansstr = "0"
        return UnsignedBigInteger(ansstr)
    }

    /**
     * Деление
     */
    operator fun div(other: UnsignedBigInteger): UnsignedBigInteger {
        val maximum = this
        val minimum = other
        when {
            maximum.data.size < minimum.data.size -> return UnsignedBigInteger("0")
            maximum == minimum -> return UnsignedBigInteger("1")
            else -> {
                var del = ""
                var ans = ""
                var step = 0
                for (i in maximum.data.size - 1 downTo 0) {
                    del += maximum.data[i].toString()
                    var a = UnsignedBigInteger(del)
                    if (a >= minimum) {
                        while (a >= minimum) {
                            a -= minimum
                            step++
                        }
                        ans = step.toString() + ans
                        step = 0
                        var b = ""
                        for (j in a.data.size - 1 downTo 0) {
                            b += a.data[j].toString()
                        }
                        del = b
                    }
                }
                var anslast = ""
                for (i in ans.length - 1 downTo 0) {
                    anslast += ans[i]
                }
                return UnsignedBigInteger(anslast)
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
    override fun equals(other: Any?): Boolean {
        if ((other !is UnsignedBigInteger) || (data.size != other.data.size)) return false
        val minimum = min(data.size, other.data.size)
        for (i in minimum - 1 downTo 0) {
            if (data[i] != other.data[i]) return false
        }
        return true
    }

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
        val int = UnsignedBigInteger(2147483647)
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
package lesson11.task1

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Tag
import java.lang.ArithmeticException

internal class UnsignedBigIntegerTest {

    @Test
    @Tag("8")
    fun plus() {
        assertEquals(UnsignedBigInteger(3), UnsignedBigInteger(2) + UnsignedBigInteger(1))
        //assertEquals(UnsignedBigInteger(2100), UnsignedBigInteger(1045) + UnsignedBigInteger(1055))
        assertEquals(UnsignedBigInteger(100000), UnsignedBigInteger(99999) + UnsignedBigInteger(1))
        assertEquals(UnsignedBigInteger(2), UnsignedBigInteger(2) + UnsignedBigInteger("000000"))
        assertEquals(UnsignedBigInteger(1002000), UnsignedBigInteger(2000) + UnsignedBigInteger(1000000))
        assertEquals(UnsignedBigInteger(4), UnsignedBigInteger(2) + UnsignedBigInteger(2))
        assertEquals(UnsignedBigInteger(13), UnsignedBigInteger(8) + UnsignedBigInteger(5))
        assertEquals(UnsignedBigInteger("9087654330"), UnsignedBigInteger("9087654329") + UnsignedBigInteger(1))
        assertEquals(
            UnsignedBigInteger("4294967295998"),
            UnsignedBigInteger("${Int.MAX_VALUE}999") + UnsignedBigInteger("${Int.MAX_VALUE}999")
        )
    }

    @Test
    @Tag("8")
    fun minus() {
        assertEquals(UnsignedBigInteger(2), UnsignedBigInteger(4) - UnsignedBigInteger(2))
        assertEquals(UnsignedBigInteger("9087654329"), UnsignedBigInteger("9087654330") - UnsignedBigInteger(1))
        assertThrows(ArithmeticException::class.java) {
            UnsignedBigInteger(2) - UnsignedBigInteger(4)
        }
        assertEquals(
            UnsignedBigInteger("9856809481"),
            UnsignedBigInteger("14151776777") - UnsignedBigInteger("4294967296")
        )
        assertEquals(UnsignedBigInteger("1"), UnsignedBigInteger("14151776777") - UnsignedBigInteger("14151776776"))
        assertEquals(
            UnsignedBigInteger("8561957452"),
            UnsignedBigInteger("18446744073") - UnsignedBigInteger("9884786621")
        )
        assertEquals(
            UnsignedBigInteger("0"),
            UnsignedBigInteger("18446744073709551616") - UnsignedBigInteger("18446744073709551616")
        )
    }

    @Test
    @Tag("12")
    fun times() {
        assertEquals(
            UnsignedBigInteger("4294967296"),
            UnsignedBigInteger("4294967296") * UnsignedBigInteger("1")
        )
        assertEquals(
            UnsignedBigInteger("15312"),
            UnsignedBigInteger("264") * UnsignedBigInteger("58")
        )
        assertEquals(
            UnsignedBigInteger("114098213790"),
            UnsignedBigInteger("365") * UnsignedBigInteger("312597846")
        )
        assertEquals(
            UnsignedBigInteger("18446744073709551616"),
            UnsignedBigInteger("4294967296") * UnsignedBigInteger("4294967296")
        )
        assertEquals(
            UnsignedBigInteger("0"),
            UnsignedBigInteger("0") * UnsignedBigInteger("256")
        )
        assertEquals(
            UnsignedBigInteger(0),
            UnsignedBigInteger(0) * UnsignedBigInteger(256)
        )
    }

    @Test
    @Tag("16")
    fun div() {
        assertEquals(
            UnsignedBigInteger("4294967296"),
            UnsignedBigInteger("18446744073709551616") / UnsignedBigInteger("4294967296")
        )
    }

    @Test
    @Tag("16")
    fun rem() {
        assertEquals(UnsignedBigInteger(5), UnsignedBigInteger(19) % UnsignedBigInteger(7))
        assertEquals(
            UnsignedBigInteger(0),
            UnsignedBigInteger("18446744073709551616") % UnsignedBigInteger("4294967296")
        )
    }

    @Test
    @Tag("8")
    fun equals() {
        assertEquals(UnsignedBigInteger(123456789), UnsignedBigInteger("123456789"))
    }

    @Test
    @Tag("8")
    fun compareTo() {
        assertTrue(UnsignedBigInteger(123456789) < UnsignedBigInteger("9876543210"))
        assertTrue(UnsignedBigInteger("9876543210") > UnsignedBigInteger(123456789))
        assertTrue(UnsignedBigInteger("111") == UnsignedBigInteger(111))
    }

    @Test
    @Tag("8")
    fun toInt() {
        assertEquals(123456789, UnsignedBigInteger("123456789").toInt())
        assertEquals(1234567895, UnsignedBigInteger("1234567895").toInt())
        assertEquals(2147483647, UnsignedBigInteger("2147483647").toInt())
        assertEquals(1234, UnsignedBigInteger("1234").toInt())
    }
}
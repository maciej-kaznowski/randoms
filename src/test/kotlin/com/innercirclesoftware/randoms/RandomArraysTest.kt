package com.innercirclesoftware.randoms

import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class RandomArraysTest {

    @Test
    fun `random ByteArray of size 0 is empty`() {
        randomByteArray(size = 0) `should be equal to` byteArrayOf()
    }

    @Test
    fun `random ByteArray of size 1 is expected`() {
        randomByteArray(size = 1) { 1 } shouldBeEqualTo byteArrayOf(1)
    }

    @Test
    fun `random ByteArray uses element provider`() {
        val result = randomByteArray(size = randomInt(min = 10, maxInclusive = 10)) { position -> position.toByte() }
        result `should be equal to` byteArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    }

    @Test
    fun `random ByteArray of negative size throws exception`() {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            randomByteArray(size = -1)
        }
    }
}
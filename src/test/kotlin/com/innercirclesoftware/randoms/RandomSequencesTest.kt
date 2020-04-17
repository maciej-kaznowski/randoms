package com.innercirclesoftware.randoms

import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.should
import org.amshove.kluent.shouldBeEmpty
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTimeout
import org.junit.jupiter.api.Test
import java.time.Duration

class RandomSequencesTest {

    @Test
    fun `test default`() {
        randomCharacterSequence()
    }

    @Test
    fun `random Sequence of Chars of size 0 is empty`() {
        randomCharacterSequence(size = 0).shouldBeEmpty()
    }

    @Test
    fun `random Sequence of Chars of fixed size results in stream of same size`() {
        val size = randomInt(min = 0, maxInclusive = 10)
        randomCharacterSequence(size = size).joinToString(separator = "") should { length == size }
    }

    @Test
    fun `random Sequence of Chars respects Char provider`() {
        val expected = listOf('0', '1', '2', '3')
        randomCharacterSequence(size = 4) { index -> "$index"[0] }.toList() `should be equal to` expected
    }

    @Test
    fun `random Sequence of Chars should not OOM for large size`() {
        assertTimeout(Duration.ofSeconds(1L)) {
            randomCharacterSequence(size = Integer.MAX_VALUE)
        }
    }

    @Test
    fun `random Sequence of Chars with negative size throws exception`() {
        assertThrows(IllegalArgumentException::class.java) {
            randomCharacterSequence(size = randomInt(min = -5, maxInclusive = -1)).toList()
        }
    }
}
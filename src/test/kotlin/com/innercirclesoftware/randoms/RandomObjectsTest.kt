package com.innercirclesoftware.randoms

import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.should
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.Duration

class RandomObjectsTest {

    @Test
    fun `works for default values`() {
        randomString() //should not throw exception
    }

    @Test
    fun `random String of size 0 is empty String`() {
        randomString(size = 0) `should be equal to` ""
    }

    @Test
    fun `random String of fixed size should have same size`() {
        val size = randomInt(min = 0, maxInclusive = 100)
        randomString(size = size) should { this.length == size }
    }

    @Test
    fun `random String respects provided characters generator`() {
        randomString(size = 4) { position -> "$position"[0] } `should be equal to` "0123"
    }

    @Test
    fun `does not timeout for default`() {
        Assertions.assertTimeout(Duration.ofSeconds(1L)) {
            randomString()
        }
    }
}
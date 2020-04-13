package com.innercirclesoftware.randoms

import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test

class RandomCollectionsTest {

    @Test
    fun `random empty list returns empty list`() {
        val emptyList = randomListOfSize(size = randomInt(min = 0, maxInclusive = 0)) { }
        emptyList `should be equal to` emptyList()
    }

    @Test
    fun `random list with fixed size is returned with correct elements`() {
        val list = randomListOfSize(size = randomInt(min = 3, maxInclusive = 3)) { it }
        list `should be equal to` listOf(0, 1, 2)
    }

    @Test
    fun `random empty set returns empty set`() {
        val emptySet = randomSetOfSize(size = randomInt(min = 0, maxInclusive = 0)) {}
        emptySet `should be equal to` emptySet()
    }

    @Test
    fun `random set of fixed size should return set of fixed size respecting duplicates from provider`() {
        val set = randomSetOfSize(size = randomInt(min = 10, maxInclusive = 10)) {
            randomInt(min = 1, maxInclusive = 10)
        }
        set `should be equal to` IntRange(start = 1, endInclusive = 10).toSet()
    }
}
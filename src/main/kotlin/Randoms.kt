package com.innercirclesoftware.randoms

import com.innercirclesoftware.randoms.Randoms.randomInt
import kotlin.random.Random

object Randoms {

    internal object Seeds {

        internal val default = Random

    }

    private val DIGITS = CharRange(start = '0', endInclusive = '9')
    private val LOWERCASE_LETTERS_ASCII = CharRange(start = 'a', endInclusive = 'z')
    private val UPPERCASE_LETTERS_ASCII = CharRange(start = 'A', endInclusive = 'Z')

    private val ALPHA_NUMERIC = (DIGITS + LOWERCASE_LETTERS_ASCII + UPPERCASE_LETTERS_ASCII).toCharArray()

    fun randomString(
        minSize: Int = 0,
        maxSizeExclusive: Int = minSize + 32,
        chars: CharArray = ALPHA_NUMERIC,
        seed: Random = Seeds.default,
        isValid: (String) -> Boolean = { true }
    ): () -> String = {
        var size: Int
        var randomString: String
        do {
            size = randomInt(min = minSize, maxExclusive = maxSizeExclusive)()
            randomString = (0 until size).map { chars.random(seed) }.fold("") { accumulated: String, nextValue: Char ->
                accumulated + nextValue
            }
        } while (isValid(randomString).not())

        randomString
    }

    fun randomInt(
        min: Int = Int.MIN_VALUE,
        maxExclusive: Int = Int.MAX_VALUE,
        seed: Random = Seeds.default
    ): () -> Int = {
        seed.nextInt(min, maxExclusive)
    }

    fun randomLong(
        min: Long = Long.MIN_VALUE,
        maxExclusive: Long = Long.MAX_VALUE,
        seed: Random = Seeds.default,
        isValid: (Long) -> Boolean = { true }
    ): () -> Long = {
        var randomLong: Long
        do {
            randomLong = seed.nextLong(min, maxExclusive)
        } while (isValid(randomLong).not())

        randomLong
    }

    fun randomDouble(
        min: Double = Double.MIN_VALUE,
        maxExclusive: Double = Double.MAX_VALUE,
        seed: Random = Seeds.default
    ): () -> Double = {
        seed.nextDouble(min, maxExclusive)
    }


    fun randomBoolean(
        seed: Random = Seeds.default
    ): () -> Boolean = {
        seed.nextBoolean()
    }
}

fun <T> (() -> T).nullable(nullableProbability: Float = 0.5F): (() -> T?) {
    return {
        if (Randoms.Seeds.default.nextFloat() <= nullableProbability) null
        else this()
    }
}

fun <K, T> ((K) -> T).nullable(nullableProbability: Float = 0.5F): ((K) -> T?) {
    return {
        if (Randoms.Seeds.default.nextFloat() <= nullableProbability) null
        else this(it)
    }
}

fun <T> listOfRandomSize(min: Int = 0, maxInclusive: Int = 10, elementProvider: (Int) -> T): () -> List<T> {
    return {
        IntRange(
            start = 0,
            endInclusive = randomInt(min = min - 1, maxExclusive = maxInclusive).invoke()
        ).map { index -> elementProvider(index) }
    }
}

fun <T> setOfRandomSize(min: Int = 0, maxInclusive: Int = 10, elementProvider: () -> T): () -> Set<T> {
    return {
        val size = randomInt(min = min, maxExclusive = maxInclusive + 1).invoke()
        val result = mutableSetOf<T>()
        while (result.size < size) {
            result += elementProvider()
        }
        result.toSet()
    }
}
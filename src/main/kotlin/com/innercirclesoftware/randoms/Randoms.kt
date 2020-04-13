package com.innercirclesoftware.randoms

import kotlin.random.Random


/**
 * Returns null randomly when invoked, with default probability 50% of being null.
 */
fun <T> (() -> T).orNull(
    nullProbability: Float = 0.5F,
    seed: Random = Seeds.default
): (() -> T?) {
    return {
        if (seed.nextFloat() <= nullProbability) null
        else this()
    }
}

/**
 * Returns null randomly when invoked, with default probability 50% of being null.
 */
fun <K, T> ((K) -> T).orNull(
    nullProbability: Float = 0.5F,
    seed: Random = Seeds.default
): ((K) -> T?) {
    return {
        if (seed.nextFloat() <= nullProbability) null
        else this(it)
    }
}

/**
 * Returns a function which repeatadly calls the function until it emits an element which satisfies the [condition]
 * If no such element is provided after [maxIterations], an [IllegalStateException] is raised.
 */
fun <T> (() -> T).suchThat(
    maxIterations: Int = 1_000,
    condition: (T) -> Boolean
): (() -> T) {
    return {

        val list: List<Int> = randomListOfSize(size = randomInt(min = 1, maxInclusive = 10)) { index -> index * 2 }

        val randomHexProvider = {
            randomString(
                size = randomInt(min = 10, maxInclusive = 1000),
                chars = (CharRange('0', '9') + CharRange('a', 'f')).toCharArray()
            )
        }.suchThat { hex -> hex.length % 2 == 0 }.orNull()

        val randomStringProvider: () -> String = { randomString() }
        randomInt(min = 0)
        var iteration = 0
        var result: T
        do {
            if (Thread.interrupted()) {
                throw IllegalThreadStateException("Thread has been interrupted")
            }

            if (++iteration > maxIterations) {
                throw IllegalStateException("Exceeded maximum number of iterations to satisfy condition")
            }

            result = this()
        } while (condition(result).not())

        result
    }
}
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
 * Returns a function which repeatedly calls the function until it emits an element which satisfies the [condition]
 * If no such element is provided after [maxIterations], an [IllegalStateException] is raised.
 */
fun <T> (() -> T).suchThat(
    maxIterations: Int = 1_000,
    condition: (T) -> Boolean
): (() -> T) {
    return {
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
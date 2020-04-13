package com.innercirclesoftware.randoms

/**
 * @return A random [List] with the provided size and elements supplied by [elementProvider]
 *
 * @param size The size of the list, defaults to a random integer in [0; 10]
 * @param elementProvider Populates the list with non-null items. The argument is the position in the [List] to provide an element for.
 */
fun <T> randomListOfSize(
    size: Int = randomInt(min = 0, maxInclusive = 10),
    elementProvider: (Int) -> T
): List<T> {
    return IntRange(
        start = 0,
        endInclusive = size - 1
    ).map { index -> elementProvider(index) }
}

/**
 * @return A random [Set] with the provided size and elements supplied by [elementProvider]
 *
 * @param size The size of the set, defaults to a random integer in [0; 10]
 * @param elementProvider Populates the list with non-null items. The argument is the position in the [List] to provide an element for.
 */
fun <T> randomSetOfSize(
    size: Int = randomInt(min = 0, maxInclusive = 10),
    elementProvider: () -> T
): Set<T> {
    val result = mutableSetOf<T>()
    while (result.size < size) {
        result += elementProvider()
    }
    return result.toSet()
}
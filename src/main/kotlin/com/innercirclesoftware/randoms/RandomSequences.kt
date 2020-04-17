package com.innercirclesoftware.randoms

/**
 * Creates a random, `Sequence<Char>` consisting of characters provided by [charProvider]
 *
 * @param size The size of the sequence. Cannot be negative. Defaults to a random integer in [0, 100]
 * @param charProvider Responsible for creating a character at the specified index in the sequence. Defaults to a random character.
 */
fun randomCharacterSequence(
    size: Int = randomInt(min = 0, maxInclusive = 100),
    charProvider: (Int) -> Char = { (Char.MIN_VALUE..Char.MAX_VALUE).random() }
): Sequence<Char> {
    require(size >= 0) { "Size cannot be negative: $size" }

    return IntRange(
        start = 0,
        endInclusive = size - 1
    ).asSequence().map { index -> charProvider(index) }
}
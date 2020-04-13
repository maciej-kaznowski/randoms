package com.innercirclesoftware.randoms

import kotlin.random.Random

private val DIGITS = CharRange(start = '0', endInclusive = '9')
private val LOWERCASE_LETTERS_ASCII = CharRange(start = 'a', endInclusive = 'z')
private val UPPERCASE_LETTERS_ASCII = CharRange(start = 'A', endInclusive = 'Z')
private val ALPHA_NUMERIC = (DIGITS + LOWERCASE_LETTERS_ASCII + UPPERCASE_LETTERS_ASCII).toCharArray()

/**
 * Returns a [String] of [size] consisting of characters in [chars]
 *
 * @param size the size of the resulting [String], defaults to a random int in [0, 31]. Cannot be negative.
 * @param chars the characters which will be present in the string, defaults to alphanumeric (a-z, A-Z, 0-9). Cannot be empty.
 * @param seed the seed to use for picking the characters
 */
fun randomString(
    size: Int = randomInt(min = 0, maxInclusive = 31),
    chars: CharArray = ALPHA_NUMERIC,
    seed: Random = Seeds.default
): String {
    require(size >= 0) { "Size must be non-negative: $size" }
    require(chars.isNotEmpty()) { "chars cannot be empty" }

    return (0 until size).map { chars.random(seed) }.fold("") { accumulated: String, nextValue: Char ->
        accumulated + nextValue
    }
}
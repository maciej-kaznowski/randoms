package com.innercirclesoftware.randoms

object Chars {

    val DIGITS = CharRange(start = '0', endInclusive = '9')
    val LOWERCASE_LETTERS_ASCII = CharRange(start = 'a', endInclusive = 'z')
    val UPPERCASE_LETTERS_ASCII = CharRange(start = 'A', endInclusive = 'Z')
    val ALPHA_NUMERIC = (DIGITS + LOWERCASE_LETTERS_ASCII + UPPERCASE_LETTERS_ASCII).toCharArray()

}

/**
 * Returns a [String] of [size] where each character is supplied by [charProvider] for a given index
 *
 * @param size the size of the resulting [String], defaults to a random int in [0, 31]. Cannot be negative.
 * @param charProvider responsible for creating a char at the given index. Defaults to a random alphanumeric (a-z, A-Z, 0-9) character.
 */
fun randomString(
    size: Int = randomInt(min = 0, maxInclusive = 31),
    charProvider: (Int) -> Char = { Chars.ALPHA_NUMERIC.random(Seeds.default) }
): String {
    require(size >= 0) { "Size cannot be negative: $size" }

    return (0 until size).map { index -> charProvider(index) }.fold("") { accumulated: String, nextValue: Char ->
        accumulated + nextValue
    }
}
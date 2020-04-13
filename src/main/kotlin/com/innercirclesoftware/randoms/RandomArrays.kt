package com.innercirclesoftware.randoms

/**
 *
 * @param [size] the size of the [ByteArray]. Must be at least 0. Defaults to a random integer in [0; Integer.MAX_VALUE]
 * @param [elementProvider] the function which is used to create the elements of the [ByteArray]. The argument is the postion in the [ByteArray]
 *
 * @return a [ByteArray] with the provided [size] and elements provided by [elementProvider]
 */
fun randomByteArray(
    size: Int = randomInt(min = 0),
    elementProvider: (Int) -> Byte = { randomByte() }
): ByteArray {
    require(size >= 0) { "Size must be non-negative: $size" }

    return ByteArray(size = size, init = elementProvider)
}

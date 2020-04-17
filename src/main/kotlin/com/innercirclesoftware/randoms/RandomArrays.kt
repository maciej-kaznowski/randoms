package com.innercirclesoftware.randoms

/**
 *
 * @param [size] the size of the [ByteArray]. Must be at least 0. Defaults to a random integer in [0; 128]. Avoid defining a large size as it could cause an OOM or take a long time to create the array.
 * @param [elementProvider] the function which is used to create the elements of the [ByteArray]. The argument is the position in the [ByteArray]
 *
 * @return a [ByteArray] with the provided [size] and elements provided by [elementProvider]
 */
fun randomByteArray(
    size: Int = randomInt(min = 0, maxInclusive = 128),
    elementProvider: (Int) -> Byte = { randomByte() }
): ByteArray {
    require(size >= 0) { "Size must be non-negative: $size" }

    return ByteArray(size = size, init = elementProvider)
}

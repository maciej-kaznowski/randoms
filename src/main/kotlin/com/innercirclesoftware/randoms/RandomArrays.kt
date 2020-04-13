package com.innercirclesoftware.randoms

fun randomByteArray(
    size: Int = randomInt(min = 0),
    elementProvider: (Int) -> Byte = { randomByte() }
): ByteArray = ByteArray(size = size, init = elementProvider)

package com.innercirclesoftware.randoms

import kotlin.random.Random

/**
 * @return a random [Int] in the range [[Integer.MIN_VALUE]; [Integer.MAX_VALUE]]
 */
fun randomInt(
    min: Int = Int.MIN_VALUE,
    maxInclusive: Int = Int.MAX_VALUE,
    seed: Random = Seeds.default
): Int = seed.nextLong(
    from = min.toLong(),
    until = maxInclusive.toLong() + 1L
).toInt()

/**
 * @return a random [Long] in the range [[Long.MIN_VALUE]; [Long.MAX_VALUE])
 */
fun randomLong(
    min: Long = Long.MIN_VALUE,
    maxExclusive: Long = Long.MAX_VALUE,
    seed: Random = Seeds.default
): Long = seed.nextLong(min, maxExclusive)

/**
 * @return a random [Double] in the range [[Double.MIN_VALUE]; [Double.MAX_VALUE])
 */
fun randomDouble(
    min: Double = Double.MIN_VALUE,
    maxExclusive: Double = Double.MAX_VALUE,
    seed: Random = Seeds.default
): Double = seed.nextDouble(min, maxExclusive)


/**
 * @return a random [Boolean] with equal probability of true/false
 */
fun randomBoolean(
    seed: Random = Seeds.default
): Boolean = seed.nextBoolean()

/**
 * @return a random [Byte] in the range [[Byte.MIN_VALUE]; [Byte.MAX_VALUE]]
 */
fun randomByte(
    min: Byte = Byte.MIN_VALUE,
    maxInclusive: Byte = Byte.MAX_VALUE,
    seed: Random = Seeds.default
): Byte = seed.nextInt(from = min.toInt(), until = maxInclusive.toInt() + 1).toByte()
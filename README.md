[![](https://jitpack.io/v/maciej-kaznowski/randoms.svg)](https://jitpack.io/#maciej-kaznowski/randoms)

## Usage:

The following functions are currently available, but more will be added when requested:
- `randomString`
- `randomInt`
- `randomLong`
- `randomDouble`
- `randomBoolean`
- `listOfRandomSize`
- `setOfRandomSize`

Usage:

```kotlin
val randomStringProvider = randomString()

val firstRandom : String = randomStringProvider()  
val secondRandom : String = randomStringProvider()  
```

You can easily allow generated values to be nullable, by appending .nullable() to the end:
```kotlin
val randomNullableStringProvider = randomString().nullable()

val randomNullable : String? = randomNullableStringProvider()
```


To create a random nullable hexadecimal string of even length in range [10, 1000)  
```kotlin
    val randomHexProvider = randomString(
        minSize = 10,
        maxSizeExclusive = 100,
        chars = (CharRange('0', '9') + CharRange('a', 'f')).toCharArray()
    ) { hex -> hex.length % 2 == 0 }.nullable()
    
    val firstEvenLengthHex : String? = randomHexProvider()
    val secondEvenHex : String? = randomHexProvider()
```

To create a list of random size in range [1, 10] consisting of even integers in increasing order starting from 0:
```kotlin
val list: List<Int> = listOfRandomSize(min = 1, maxInclusive = 10) { index -> index * 2 }.invoke()
```




## Installation:
### Gradle:
In your your `build.gradle` add: 
```
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

In your module `build.gradle` add:
```
dependencies {
    implementation 'com.github.maciej-kaznowski:randoms:master-SNAPSHOT'
}
```

### Maven:
```
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

```
<dependency>
    <groupId>com.github.maciej-kaznowski</groupId>
    <artifactId>randoms</artifactId>
    <version>master-SNAPSHOT</version>
</dependency>
```
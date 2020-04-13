[![](https://jitpack.io/v/maciej-kaznowski/randoms.svg)](https://jitpack.io/#maciej-kaznowski/randoms)

## Randoms:

The following randoms are currently available, but more will be added when requested:

- ##### Primitives:
    - `randomInt`
    - `randomLong`
    - `randomDouble`
    - `randomBoolean`
    - `randomByte`
    
- ##### Objects:
    - `randomString`

- ##### Collections:
    - `randomListOfSize`
    - `randomSetOfSize`
    
- ##### Arrays:
    - `randomByteArray`

## Usage:

```kotlin
val randomString: String = randomString()
val randomPositiveInt: Int = randomInt(min = 1)
```

If you need to add additional conditions on top of the random variables, just wrap them in a lambda:
```kotlin
val randomStringProvider: () -> String = { randomString() }
```

Then you can easily allow generated values to be nullable, by appending .nullable() to the end:
```kotlin
val randomNullable: String? = { randomString() }.nullable(nullProbability = 0.5F).invoke()
```

### Example 1
To create a random nullable hexadecimal string of even length in range [10, 1000]  
```kotlin
val randomHexProvider = { randomString(
        size = randomInt(min = 10, maxInclusive = 1000),
        chars = (CharRange('0', '9') + CharRange('a', 'f')).toCharArray()
)}.suchThat { hex -> hex.length % 2 == 0 }.orNull()

val firstEvenLengthHex : String? = randomHexProvider()
val secondEvenLengthHex : String? = randomHexProvider()
```

### Example 2
To create a list of random size in range [1, 10] consisting of even integers in increasing order starting from 0:
```kotlin
val list: List<Int> = randomListOfSize(size = randomInt(min = 1, maxInclusive = 10)) { index -> index * 2 }
```




## Installation:
### Gradle:
In your root `build.gradle` add: 
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
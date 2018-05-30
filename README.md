# NcUtils
[![Release](https://jitpack.io/v/noelchew/NcUtils.svg)](https://jitpack.io/#noelchew/NcUtils)

Utilities for Android development.

- Image and video picker with Runtime permissions.
- Google map utilities
- Shared preference and internal storage utilities
- Alert dialog utilities
- and more...


# Integration
This library is hosted by jitpack.io.

Root level gradle:
```
allprojects {
 repositories {
    jcenter()
    google()
    maven { url "https://jitpack.io" }
 }
}
```

Application level gradle:

[![Release](https://jitpack.io/v/noelchew/NcUtils.svg)](https://jitpack.io/#noelchew/NcUtils)

```
dependencies {
    implementation 'com.github.noelchew:NcUtils:x.y.z'
}
```
Note: do not add the jitpack.io repository under buildscript

If you are using Espresso, please force the findbug dependency as shown below:
```
android {
    // ...

    // add this if you are using espresso
    configurations.all {
        resolutionStrategy.force 'com.google.code.findbugs:jsr305:3.0.1'
    }
}
```

# Used by
Please let me know at chewwengchuen [at] gmail [dot] com to add to this list.

# License
The content of this project itself is licensed under the Creative Commons Attribution 3.0 license, and the underlying source code used to format and display that content is licensed under the MIT license.

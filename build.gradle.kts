plugins {
    kotlin("js") version "1.5.10"

    // INTERESTING - Required for Ktor Serialisation to work
    kotlin("plugin.serialization") version "1.5.0"
}

group = "co.instil"
version = "1.0-SNAPSHOT"
val ktorVersion = "1.5.4"

repositories {
    maven("https://kotlin.bintray.com/kotlin-js-wrappers/")
    mavenCentral()
    jcenter()
}

dependencies {
    implementation("org.jetbrains:kotlin-react:17.0.1-pre.148-kotlin-1.4.30")
    implementation("org.jetbrains:kotlin-react-dom:17.0.1-pre.148-kotlin-1.4.30")
    implementation("org.jetbrains:kotlin-react-router-dom:5.2.0-pre.148-kotlin-1.4.30")
    implementation("org.jetbrains:kotlin-redux:4.0.5-pre.148-kotlin-1.4.30")
    implementation("org.jetbrains:kotlin-react-redux:7.2.2-pre.148-kotlin-1.4.30")

    implementation("io.ktor:ktor-client-js:$ktorVersion")
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-serialization-js:$ktorVersion")

//    implementation(npm("react", "16.13.1"))
//    implementation(npm("react-dom", "16.13.1"))
//    implementation(npm("react-redux", "7.2.1"))
    implementation(npm("@react-three/fiber", "7.0.1"))
    implementation(npm("react-use-gesture", "9.1.3"))
//    implementation(npm("redux", "4.0.5"))
    implementation(npm("three", "0.129.0"))
}

kotlin {
    js(LEGACY) {
        binaries.executable()
        browser {
            commonWebpackConfig {
                cssSupport.enabled = true
                devServer = devServer?.copy(port = 3000)
            }
        }
    }
}

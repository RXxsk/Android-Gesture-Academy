kotlin
pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Android-Gesture-Academy"
include(":library:gesture-core")
//include(":library:accessibility") // Lo agregaremos después
//include(":library:samples") // Lo agregaremos después
//include(":plugin") // Lo agregaremos después

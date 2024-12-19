pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    plugins {
        // Actualiza la versión de Kotlin a 1.9.10
        id("com.android.application") version "8.7.3"
        id("org.jetbrains.kotlin.android") version "1.9.10"
        // Ajustar KSP a una versión compatible con Kotlin 1.9.10
        // Consulta https://github.com/google/ksp/releases para la versión disponible.
        // Ejemplo: 1.9.10-1.0.13 (si existe)
        id("com.google.devtools.ksp") version "1.9.10-1.0.13"
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "FullyTrilingual"
include(":app")

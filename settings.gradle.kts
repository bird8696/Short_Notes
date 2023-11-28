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
        repositories {
            maven { url = uri("https://www.jitpack.io" ) }
            maven("https://maven.agora.io/repository/release")
        }
    }
}

rootProject.name = "Short Notes"
include(":app")

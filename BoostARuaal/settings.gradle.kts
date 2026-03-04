rootProject.name = "BoostAR-uaal"
include(":app",  /*":unityLibrary"*/)
//project(":unityLibrary").projectDir = File(rootProject.projectDir, "../unityAr/unityLibrary")

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        /*
        flatDir {
            dirs("${rootDir}/../unityAr/unityLibrary/libs")
        }
        */
    }
}


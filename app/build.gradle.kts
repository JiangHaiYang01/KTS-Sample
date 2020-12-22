plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    compileSdkVersion(project.compileSdk)
    buildToolsVersion(project.buildTools)

    defaultConfig {
        applicationId(project.appId)
        minSdkVersion(project.minSdk)
        targetSdkVersion(project.targetSdk)
        versionCode(project.versionCode)
        versionName(project.versionName)

        testInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner")
    }

    buildTypes {
        getByName("release") {
            minifyEnabled(false)
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

        }
    }
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    addDefaultDependencies()
}
import com.android.build.gradle.internal.api.BaseVariantOutputImpl
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.Date

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.sonarqube)
    id(libs.plugins.kotlin.kapt.get().pluginId)
    id(libs.plugins.kotlin.parcelize.get().pluginId)
    id(libs.plugins.ksp.get().pluginId)
    id(libs.plugins.hilt.android.plugin.get().pluginId)
    id(libs.plugins.navigation.safe.args.get().pluginId)
}

android {
    namespace = "com.yizhenwind.keeper"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.yizhenwind.keeper"
        minSdk = 26
        targetSdk = 34
        versionCode = 2
        versionName = "1.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    applicationVariants.all {
        outputs
            .map { it as BaseVariantOutputImpl }
            .forEach { output ->
                val dateFormat = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA)
                val outputFileName =
                    "Keeper-$versionName-$name-${dateFormat.format(Date())}.${output.outputFile.extension}"
                output.outputFileName = outputFileName
            }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    buildFeatures {
        dataBinding = true
    }

    ksp {
        arg("room.generateKotlin", "true")
    }
}

sonar {
    properties {
        property("sonar.projectKey", "WangZhiYao_Keeper_AYvlfINHoN-SazKCPsMv")
        property("sonar.projectName", "Keeper")
    }
}

dependencies {

    testImplementation(libs.junit)
    androidTestImplementation(libs.bundles.androidx.test)

    debugImplementation(libs.leakcanary.android)

    implementation(libs.bundles.kotlinx.coroutines)

    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.bundles.androidx.lifecycle)
    implementation(libs.bundles.androidx.navigation)

    implementation(libs.bundles.room)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.paging.runtime.ktx)
    implementation(libs.androidx.datastore.preferences)

    implementation(libs.google.material)

    implementation(libs.bundles.orbit.mvi)

}
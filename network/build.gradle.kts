import org.gradle.internal.impldep.com.amazonaws.PredefinedClientConfigurations.defaultConfig
import org.gradle.internal.impldep.com.jcraft.jsch.ConfigRepository.defaultConfig
import java.util.Properties

plugins {
  id("kotlin-kapt")
  id("com.android.library")
  id("com.google.dagger.hilt.android") version "2.52" apply false
  id("org.jetbrains.kotlin.plugin.serialization") version "2.0.10" apply false
  alias(libs.plugins.kotlin.android)

}

val localProperties = Properties().apply {
  load(rootProject.file("local.properties").inputStream())
}

android {
  namespace = "com.sample.app.network"
  compileSdk = 35

  kotlinOptions {
    jvmTarget = "1.8"
  }

  android.buildFeatures.buildConfig = true

  defaultConfig {
    minSdk = 24

    buildConfigField("String", "PUBLIC_API_KEY", "\"${localProperties.getProperty("PUBLIC_API_KEY")}\"")
    buildConfigField("String", "PRIVATE_API_KEY", "\"${localProperties.getProperty("PRIVATE_API_KEY")}\"")
  }
}


dependencies {
  implementation(libs.hilt.android)
  kapt(libs.dagger.hilt.android.compiler)

  api(libs.okhttp.logging)
  api(libs.retrofit.core)
  api (libs.converter.gson)

  api(libs.retrofit.result.adapter)
  api (libs.gson)


  api(libs.retrofit.kotlin.serialization)
  api(libs.kotlinx.serialization.json)
  implementation(libs.androidx.core.ktx)
}

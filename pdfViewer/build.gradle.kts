import com.vanniktech.maven.publish.AndroidSingleVariantLibrary
import com.vanniktech.maven.publish.SonatypeHost

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("org.jetbrains.dokka") version "1.9.20"
    id("com.vanniktech.maven.publish") version "0.28.0"
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.0"
}

android {
    namespace = "com.rajat.pdfviewer"
    compileSdk = 34

    defaultConfig {
        minSdk = 21
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    kotlin {
        jvmToolchain(17)
    }

    buildFeatures {
        viewBinding = true
        compose = true
    }

}

dependencies {
    val kotlin_version = "2.0.0"
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version")
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0-RC")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")
    implementation("com.google.android.material:material:1.12.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.4")
    implementation("androidx.activity:activity-ktx:1.9.1")
    // compose
    implementation(platform("androidx.compose:compose-bom:2024.06.00"))
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.06.00"))
    implementation("androidx.compose.ui:ui:1.6.8")
    // Android Studio Preview support
    implementation("androidx.compose.ui:ui-tooling-preview:1.6.8")
    debugImplementation("androidx.compose.ui:ui-tooling:1.6.8")
    // UI Tests
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    implementation("androidx.activity:activity-compose:1.9.1")
}

mavenPublishing {
    configure(
        AndroidSingleVariantLibrary(
            // the published variant
            variant = "release",
            // whether to publish a sources jar
            sourcesJar = true,
        )
    )

    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
    signAllPublications()

    coordinates("io.github.bastionkid", "Pdf-Viewer", "2.1.2")

    pom {
        name.set("PDF Viewer")
        description.set("A PDF viewing library for Android")
        url.set("https://github.com/bastionkid/Pdf-Viewer")
        licenses {
            license {
                name.set("MIT License")
                url.set("https://opensource.org/licenses/MIT")
            }
        }
        developers {
            developer {
                id.set("bastionkid")
                name.set("Akash Khunt")
                email.set("akash.mercer@gmail.com")
            }
        }
        scm {
            connection.set("scm:git:git://github.com/bastionkid/Pdf-Viewer.git")
            developerConnection.set("scm:git:ssh://github.com/bastionkid/Pdf-Viewer.git")
            url.set("https://github.com/bastionkid/Pdf-Viewer")
        }
    }

}
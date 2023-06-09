# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# retracing of your application's stack traces
-keepattributes LineNumberTable,SourceFile
-renamesourcefileattribute SourceFile

# Ignore data file
-keepnames @kotlin.Metadata class com.example.ca_compte.data.**
-keep class com.example.ca_compte.data.** { *; }
-keepclassmembers class com.example.ca_compte.data.** { *; }

# You can specify any path and filename.
-printconfiguration /tmp/full-r8-config.txt
-printusage
-printseeds
-printmapping mapping.txt

-keepattributes Exceptions,InnerClasses,Signature,Deprecated,
                SourceFile,LineNumberTable,*Annotation*,EnclosingMethod

# Kotlin rules
-dontwarn kotlin.**
-keep class kotlin.** { *; }

# AndroidX rules
-dontwarn androidx.**
-keep class androidx.** { *; }

# Material design rules
-dontwarn com.google.android.material.**
-keep class com.google.android.material.** { *; }

# Constraint layout rules
-dontwarn androidx.constraintlayout.**
-keep class androidx.constraintlayout.** { *; }

# Navigation component rules
-dontwarn androidx.navigation.**
-keep class androidx.navigation.** { *; }

# Lifecycle rules
-dontwarn androidx.lifecycle.**
-keep class androidx.lifecycle.** { *; }

# Retrofit rules
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

# OkHttp rules
-dontwarn okhttp3.**
-keep class okhttp3.** { *; }
-dontwarn okio.**
-dontwarn javax.annotation.**
-dontwarn org.conscrypt.**

# Moshi rules
-dontwarn com.squareup.moshi.**
-keepclassmembers class * {
    @com.squareup.moshi.FromJson *;
    @com.squareup.moshi.ToJson *;
}

# Dagger rules
-dontwarn com.google.dagger.**
-keep class com.google.dagger.** { *; }
-keep class * implements dagger.*
-keepclassmembers class ** {
    @dagger.* <methods>;
}
-keepclassmembers class ** {
    void *(**On*Event);
}

# Coroutines rules
-dontwarn kotlinx.coroutines.**
-keep class kotlinx.coroutines.** { *; }

# Hilt rules
-keep class androidx.hilt.** { *; }
#-keep @dagger.hilt.android.internal.lifecycle.HiltViewModel. class * { *; }
-keep class * implements androidx.lifecycle.ViewModel
#-keep @androidx.hilt.lifecycle.ViewModelInject. class *

# Keep class names of Hilt injected ViewModels since their name are used as a multibinding map key.
-keepnames @dagger.hilt.android.lifecycle.HiltViewModel class * extends androidx.lifecycle.ViewModel
# Parcel library
-keep interface org.parceler.Parcel
-keep @org.parceler.Parcel class * { *; }
-keep class **$$Parcelable { *; }

-dontwarn icepick.**
-keep class icepick.** { *; }
-keep class **$$Icepick { *; }
-keepclasseswithmembernames class * {
    @icepick.* <fields>;
}
-keepnames class * { @icepick.State *;}

#ButterKnife rules
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

# Retrofit rules
-dontwarn retrofit.**
-keep class retrofit.** { *; }
-keepattributes Signature
-keepattributes Exceptions

# OkHttp rules
-dontwarn okio.**
-dontwarn com.squareup.okhttp.**

# Otto rules
-keepclassmembers class ** {
    @com.squareup.otto.Subscribe public *;
    @com.squareup.otto.Produce public *;
}

# RxJava rules
# RxAndroid will soon ship with rules so this may not be needed in the future
# https://github.com/ReactiveX/RxAndroid/issues/219
-dontwarn sun.misc.Unsafe
-keep class rx.internal.util.unsafe.** { *; }

# Gson rules
-keepattributes Signature
-keep class sun.misc.Unsafe { *; }
# TODO change to match your package model
# Keep non static or private fields of models so Gson can find their names
-keepclassmembers class com.ohoussein.showreader.data.model.** {
    !static !private <fields>;
}
# TODO change to match your Retrofit services (only if using inner models withing the service)
# Some models used by gson are inner classes inside the retrofit service
-keepclassmembers class com.ohoussein.showreader.data.remote.RibotsService$** {
    !static !private <fields>;
}

# Produces useful obfuscated stack traces
# http://proguard.sourceforge.net/manual/examples.html#stacktrace
-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable
# TODO
-keep class com.ohoussein.showreader.model.**               { *; }
#------------------
-printmapping mapping.txt
-verbose
#-libraryjars libs
-keep class android.support.v7.app.** { *; }
-keep interface android.support.v7.app.** { *; }
-keep interface android.support.v7.**{ *; }
-keep class butterknife.** { *; }
-dontwarn android.support.**
-dontwarn com.eowise.**
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
 @butterknife.* <methods>;
}



-dontwarn com.malinskiy.superrecyclerview.SwipeDismissRecyclerViewTouchListener*
-keep class com.joanzapata.** { *; }
-keep class com.eowise.**{ *; }
-keep class android.support.v7.widget.RecyclerView
-keep class * extends android.support.v7.widget.RecyclerView

-keep class com.daimajia.easing.** { *; }
-keep interface com.daimajia.easing.** { *; }
-dontwarn org.apache.commons.**
-keep class org.apache.http.** { *; }
-keep class android.support.v7.widget.SearchView { *; }
# ActiveAndroid
-keep class com.activeandroid.** { *; }
-keep class com.activeandroid.**.** { *; }
-keep class * extends com.activeandroid.Model
-keep class * extends com.activeandroid.serializer.TypeSerializer

# Only required if not using the Spring RestTemplate
-dontwarn org.androidannotations.api.rest.**
# http://avro.apache.org/

-keep class org.apache.avro.** { *; }
-keep class org.apache.commons.logging.**
-dontwarn org.apache.commons.logging.impl.**
# These options are the minimal options for a functioning application
# using Proguard and the AWS SDK 2.1.5 for Android

-keep class org.apache.commons.logging.**               { *; }
-keep class com.amazonaws.org.apache.commons.logging.** { *; }
-keep class com.amazonaws.services.sqs.QueueUrlHandler  { *; }
-keep class com.amazonaws.javax.xml.transform.sax.*     { public *; }
-keep class com.amazonaws.javax.xml.stream.**           { *; }
-keep class com.amazonaws.services.**.model.*Exception* { *; }
-keep class com.amazonaws.internal.**                   { *; }
-keep class org.codehaus.**                             { *; }
-keep class org.joda.time.tz.Provider                   { *; }
-keep class org.joda.time.tz.NameProvider               { *; }
-keepattributes Signature,*Annotation*,EnclosingMethod
-keepnames class com.fasterxml.jackson.** { *; }
-keepnames class com.amazonaws.** { *; }

-dontwarn com.fasterxml.jackson.databind.**
-dontwarn javax.xml.stream.events.**
-dontwarn org.codehaus.jackson.**
-dontwarn org.apache.commons.logging.impl.**
-dontwarn org.apache.http.conn.scheme.**
-dontwarn org.apache.http.annotation.**
-dontwarn org.ietf.jgss.**
-dontwarn org.joda.convert.**
-dontwarn com.amazonaws.org.joda.convert.**
-dontwarn org.w3c.dom.bootstrap.**

#SDK split into multiple jars so certain classes may be referenced but not used
-dontwarn com.amazonaws.services.s3.**
-dontwarn com.amazonaws.services.sqs.**

-dontnote com.amazonaws.services.sqs.QueueUrlHandler
# ButterKnife 6

-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewInjector { *; }
-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}
# Crashlytics 1.+

-keep class com.crashlytics.** { *; }
-keepattributes SourceFile,LineNumberTable
## Square Otto specific rules ##
## https://square.github.io/otto/ ##

-keepclassmembers class ** {
    @com.squareup.otto.Subscribe public *;
    @com.squareup.otto.Produce public *;
}

-keep class com.squareup.okhttp.** { *; }
-keep interface com.squareup.okhttp.** { *; }
-dontwarn com.squareup.okhttp.**

-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn okhttp3.**

-dontwarn rx.**
-dontwarn retrofit.**
-dontwarn retrofit2.**
-dontwarn okio.**
-keep class retrofit.** { *; }
-keep class retrofit2.** { *; }
-keepclasseswithmembers class * {
    @retrofit.http.* <methods>;
}
-keep public class android.support.v7.widget.** { *; }
-keep public class android.support.v7.internal.widget.** { *; }
-keep public class android.support.v7.internal.view.menu.** { *; }

-keep public class * extends android.support.v4.view.ActionProvider {
    public <init>(android.mContent.Context);
}
-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontpreverify
-verbose
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.mContent.BroadcastReceiver
-keep public class * extends android.mContent.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService

-keepclasseswithmembernames class * {
    native <methods>;
}

-keepclasseswithmembers class * {
    public <init>(android.mContent.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
    public <init>(android.mContent.Context, android.util.AttributeSet, int);
}

-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

-dontwarn org.joda.convert.**
-dontwarn org.joda.time.**
-keep class org.joda.time.** { *; }
-keep interface org.joda.time.** { *; }
-dontwarn android.support.design.**
-keep class android.support.design.** { *; }
-keep interface android.support.design.** { *; }
-keep public class android.support.design.R$* { *; }
-keep class org.eclipse.paho.client.mqttv3.**               { *; }

-keep class android.databinding.** { *; }
-keepnames class * implements java.io.Serializable
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
-keepattributes *Annotation*
-keepattributes -dontwarn java.lang.invoke.*.*
-keepattributes javax.annotation.processing.*
-keepclassmembers class * extends java.lang.Enum { *; }
-keepclasseswithmembernames class android.**
-keepclasseswithmembernames interface android.**
# -dontobfuscate
#-libraryjars  /usr/lib/jvm/java-7-openjdk-amd64/lib/rt.jar
#-libraryjars  /usr/lib/jvm/java-7-openjdk-amd64/lib/jce.jar
-keep class javax.faces.** { *; }
-keep class com.ocpsoft.pretty.time.** { *; }
-dontwarn com.ocpsoft.pretty.time.**
-dontnote com.ocpsoft.pretty.time.**

-dontwarn javax.lang.**
-dontwarn java.beans.**
-dontwarn org.easymock.**
-dontwarn org.jmock.**
-dontwarn android.support.test.**
-dontwarn junit.**
-dontwarn org.junit.**
-dontwarn android.databinding.**

#glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

-keep class com.kaichunlin.transition.** { *; }
-dontwarn com.kaichunlin.transition.**

-keep class com.kaichunlin.transition.** { *; }
-dontwarn com.kaichunlin.transition.**

-keep class com.kennyc.view.** { *; }
-dontwarn com.kennyc.view.**

-keep class java.lang.invoke.** { *; }
-dontwarn java.lang.invoke.**

-keep class com.kaichunlin.transition.** { *; }
-dontwarn com.kaichunlin.transition.**

-keep class jp.wasabeef.glide.** { *; }
-dontwarn jp.wasabeef.glide.**

-keep class org.apache.commons.codec.binary.**
-keep interface org.apache.commons.codec.binary.**
-keep enum org.apache.commons.codec.binary.**
-keep class org.slf4j.**
-keep interface org.slf4j.**
-keep enum org.slf4j.**
-keep class com.sun.syndication.io.**
-keep interface com.sun.syndication.io.**
-keep enum com.sun.syndication.io.**
-keep class com.sun.syndication.feed.synd.**
-keep interface com.sun.syndication.feed.synd.**
-keep enum com.sun.syndication.feed.synd.**

-keep public class org.jsoup.** {
public *;
}
-keeppackagenames org.jsoup.nodes

-keep class net.jodah.failsafe.** { *; }
-dontwarn net.jodah.failsafe.**

# ?!
#RX !
-keep class rx.** { *; }
-dontwarn rx.**

-keepattributes EnclosingMethod
-dontwarn javax.annotation.Nullable
-dontwarn javax.annotation.**

#Fresco
# Keep our interfaces so they can be used by other ProGuard rules.
# See http://sourceforge.net/p/proguard/bugs/466/
-keep,allowobfuscation @interface com.facebook.common.internal.DoNotStrip

# Do not strip any method/class that is annotated with @DoNotStrip
-keep @com.facebook.common.internal.DoNotStrip class *
-keepclassmembers class * {
    @com.facebook.common.internal.DoNotStrip *;
}

# Keep native methods
-keepclassmembers class * {
    native <methods>;
}

-dontwarn okio.**
-dontwarn com.squareup.okhttp.**
-dontwarn okhttp3.**
-dontwarn javax.annotation.**
-dontwarn com.android.volley.toolbox.**
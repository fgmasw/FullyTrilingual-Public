# Añade aquí reglas específicas del proyecto, si es necesario.
# proguardFiles en build.gradle controla el uso de estos archivos.

# Reglas comentadas por defecto para WebView con JS y preservación de información de depuración.
# Puedes descomentarlas o modificarlas si las necesitas.

-dontwarn android.database.**
-dontwarn dalvik.system.VMDebug
-keepattributes *Annotation*
-keep class androidx.** { *; }
-keep class kotlinx.** { *; }
-keep class android.os.** { *; }
-dontwarn android.os.**

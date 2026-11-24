# Casa Mascotas Los Tomates 📱

Proyecto académico **Java + XML**, estilo Android clásico con **Android Support Library 28**; compatible a nivel de código con **Android 4.0.3, API 15 (minSdk 15)**. No usa Kotlin, Compose ni AndroidX.

## Requisitos de importación

- Android Studio que admita **Android Gradle Plugin 8.7.3** (por ejemplo Ladybug o posterior).
- JDK **17** en Gradle JDK (también puede utilizarse JDK 21 con Gradle 8.9).
- SDK Platform **Android 15 (API 35)** instalado para **compilar** (no confundir con `minSdk 15` / Android 4.0.3).
- Conexión a Internet durante **la primera sincronización** para descargar Gradle y Maven dependencies.
- Emulador o dispositivo con Android **API 15 o superior**. Las imágenes de sistema API 15 son antiguas y puede que no estén disponibles o no funcionen con virtualización moderna; si no hay imagen API 15, prueba API 23 para demostrar interfaz y navegación.

## Importación

1. Descomprime el ZIP; **abre la carpeta raíz** `CasaMascotasLosTomates`, no `app`.
2. En Android Studio selecciona `Open`, escoge la carpeta y confía en el proyecto.
3. Si Android Studio lo solicita, instala **Android SDK Platform 35**.
4. En Settings > Build, Execution, Deployment > Build Tools > Gradle elige **Gradle JDK 17**.
5. Permite la sincronización Gradle y la descarga de dependencias.
6. Crea un dispositivo virtual o conecta un Android con API >= 15 y presiona `Run`.

### Compilación de depuración

Con JDK 17, Android SDK Platform 35 y Android SDK Build-Tools 34.0.0 instalados, define `ANDROID_HOME` con la ruta del SDK si compilas fuera de Android Studio. Ejecuta desde la raíz del proyecto:

```sh
./gradlew :app:assembleDebug
```

En Windows usa `gradlew.bat :app:assembleDebug`. El wrapper oficial descarga Gradle 8.9 y verifica el SHA-256 de la distribución. El APK queda en `app/build/outputs/apk/debug/app-debug.apk`.

## Estructura y flujos

- `Mascota.java`: entidad `Mascota`.
- `MascotaDataSet.java`: nueve mascotas dummy + cinco hardcodeadas para pantalla de estrella.
- `MascotaViewHolder.java`: ViewHolder del item.
- `MascotaAdapter.java`: Adapter que vincula el dataset y recibe eventos de clic.
- `MainActivity.java`: Toolbar con estrella y overflow (`Contacto`, `Acerca De`), `TabLayout` y `ViewPager`.
- `MascotasFragment.java`: RecyclerView y botón flotante de subir (aparece tras desplazar).
- `FavoritosFragment.java`: muestra mascotas con huesos activados.
- `PerfilFragment.java`: foto circular con librería `CircularImageView:3.0.2` + RecyclerView de 3 columnas.
- `UltimasCincoActivity.java`: **exactamente cinco** mascotas dummy y flecha de regreso al Activity padre.
- `ContactoActivity.java` + `EmailSender.java`: TextInputLayout con nombre/correo/mensaje y **JavaMail para Android** 1.6.7 en API >= 19. Pide host, puerto, correo emisor, contraseña de aplicación y destinatario en diálogo; no guarda credenciales. En **API 15-18**, ofrece abrir una app de correo con `ACTION_SENDTO` porque la biblioteca oficial JavaMail requiere API 19 como mínimo.
- `AcercaDeActivity.java`: bio del equipo desarrollador editable.
- Carpeta `res/drawable-nodpi`: logo tipo Paint y fotografías ficticias de mascotas generadas para la app.

## Pruebas manuales sugeridas

1. Abrir app; se muestra splash y luego la pestaña `MASCOTAS` con 9 mascotas.
2. Tocar el hueso blanco de Max: pasa a amarillo y contador 12→13. Repetir: vuelve a 12 y hueso blanco. Se guarda entre reinicios.
3. Desplazarse hacia Misha y Zeus: aparece botón flotante, pulsarlo para volver arriba.
4. Deslizar horizontalmente `MASCOTAS → FAVORITOS → PERFIL`; perfil muestra foto circular y Grid RecyclerView de 9 imágenes.
5. Pulsar estrella de la Toolbar: se abre `Últimas 5 mascotas` con cinco datos fijos; usar flecha atrás.
6. Abrir overflow (⋮): `Contacto` lleva al formulario y `Acerca De` a bio. Ambas tienen regreso a la actividad principal.
7. Escribir nombre, correo y comentario. En **API 19+**, pulsar `ENVIAR COMENTARIO`, completar SMTP y comprobar resultado; solo mostrar éxito si el servidor aceptó el correo. En **API 15**, se abre un cliente de correo instalado (si existe) porque JavaMail oficial no soporta API 15.

## Compatibilidad y verificación

- `minSdk 15` permite instalar el APK en Android 4.0.3 y 4.0.4. La ejecución automatizada se hizo con una imagen Android 4.0.4 (API 15). Consulta `VERIFICACION.txt` para ver el alcance.
- [JavaMail para Android](https://javaee.github.io/javamail/Android) requiere API 19. En API 15–18, Contacto abre el cliente de correo mediante `ACTION_SENDTO`. En API 19 o posterior, JavaMail usa SMTP con STARTTLS obligatorio y verifica el nombre del servidor en el certificado.
- Usa un servidor SMTP y credenciales válidas para el envío directo. El formulario solicita estos datos para cada envío. El contenido de pantalla y las fotografías son ficticios.
- La compilación mantiene `targetSdk 28`. Android Lint marca ese valor con `ExpiredTargetSdkVersion` por los requisitos de publicación en Google Play. El APK generado es de depuración.

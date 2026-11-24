# Casa Mascotas Los Tomates — versión sin SQLite

Proyecto Android Java/XML compatible con Android 4.0.3 (`minSdk 15`).

## Contenido

- `proyecto/`: proyecto importable en Android Studio.
- `imgs/`: capturas tomadas durante la ejecución en un emulador Android 4.0.4 API 15.

## Compilación

```bash
cd proyecto
./gradlew assembleDebug
```

Esta versión conserva las funcionalidades con RecyclerView, ViewPager, Fragments y JavaMail, sin persistencia SQLite.

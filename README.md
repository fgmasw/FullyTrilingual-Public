# FullyTrilingual

¡Bienvenido(a) a **FullyTrilingual**!  
Este proyecto es una **aplicación Android** que permite **agregar, listar, editar y eliminar** palabras en **tres idiomas**: español (ES), inglés (EN) y portugués (PT).

## Funcionalidades principales
1. **Selección de idioma** en la pantalla principal (ES, EN, PT).  
2. **Agregar** palabras con sus frases de ejemplo en los tres idiomas.  
3. **Listar** todas las palabras almacenadas (usando **Room**).  
4. **Editar** o **eliminar** palabras específicas.

## Tecnologías utilizadas
- **Kotlin** con **Jetpack Compose** (UI declarativa).
- **Room** para la base de datos local.
- **MVVM** (ViewModel y Repository) para la arquitectura.
- **Navigation Compose** para la navegación entre pantallas.

## Estructura básica
- **MainActivity.kt**: Configura la navegación y crea el repositorio.
- **data/**: DAO, base de datos (`AppDatabase`), entidades (`Word`), repositorio (`WordRepository`).
- **ui/**: Pantallas principales (Home, AddWord, EditWord, WordList) y componentes reutilizables.
- **utils/**: Strings traducidas (ES/EN/PT) y utilidades generales.

## Cómo ejecutar
1. Clona o descarga este repositorio.
2. Ábrelo en **Android Studio**.
3. Configura un **emulador** o un **dispositivo físico**.
4. Haz clic en **Run** para compilar e instalar la app.

## Uso
- **HomeScreen**: Selecciona un idioma y ve a “Agregar palabra” o a la “Lista de palabras”.
- **AddWordScreen**: Rellena los campos (ES, EN, PT) y guarda la palabra.
- **WordListScreen**: Muestra la lista completa de palabras (edición y borrado disponibles).
- **EditWordScreen**: Modifica la palabra seleccionada y guarda los cambios.

## Demo en video
Si deseas ver cómo funciona la aplicación, puedes echar un vistazo a la demo en YouTube:  
[Demo de FullyTrilingual](https://www.youtube.com/watch?v=9ZK3--IHv_0)

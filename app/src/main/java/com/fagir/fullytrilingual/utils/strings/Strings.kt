package com.fagir.fullytrilingual.utils.strings

/**
 * Objeto que almacena los textos de la aplicación en distintos idiomas.
 *
 * Cada propiedad se asocia a un map de la forma:
 *   "es" -> "Texto en Español"
 *   "en" -> "Texto en Inglés"
 *   "pt" -> "Texto en Portugués"
 *
 * Cuando necesites más idiomas, solo agrega la clave y traducción correspondiente.
 */
object Strings {

    // Ejemplo para la pantalla de inicio (HomeScreen)
    val homeTitle = mapOf(
        "es" to "Pantalla de Inicio",
        "en" to "Home Screen",
        "pt" to "Tela Inicial"
    )

    val selectLanguageHint = mapOf(
        "es" to "Selecciona un idioma",
        "en" to "Select a language",
        "pt" to "Selecione um idioma"
    )

    val goToAddWord = mapOf(
        "es" to "Ir a Agregar Palabra",
        "en" to "Go to Add Word",
        "pt" to "Ir para Adicionar Palavra"
    )

    val goToWordList = mapOf(
        "es" to "Ir a Lista de Palabras",
        "en" to "Go to Word List",
        "pt" to "Ir para Lista de Palavras"
    )

    // Ejemplo para la pantalla de agregar palabra (AddWordScreen)
    val addWordTitle = mapOf(
        "es" to "Agregar palabra nueva",
        "en" to "Add new word",
        "pt" to "Adicionar nova palavra"
    )

    val wordEsLabel = mapOf(
        "es" to "Palabra (ES)",
        "en" to "Word (ES)",
        "pt" to "Palavra (ES)"
    )

    val wordEnLabel = mapOf(
        "es" to "Palabra (EN)",
        "en" to "Word (EN)",
        "pt" to "Palavra (EN)"
    )

    val wordPtLabel = mapOf(
        "es" to "Palabra (PT)",
        "en" to "Word (PT)",
        "pt" to "Palavra (PT)"
    )

    val phraseEsLabel = mapOf(
        "es" to "Frase (ES)",
        "en" to "Phrase (ES)",
        "pt" to "Frase (ES)"
    )

    val phraseEnLabel = mapOf(
        "es" to "Frase (EN)",
        "en" to "Phrase (EN)",
        "pt" to "Frase (EN)"
    )

    val phrasePtLabel = mapOf(
        "es" to "Frase (PT)",
        "en" to "Phrase (PT)",
        "pt" to "Frase (PT)"
    )

    val buttonSave = mapOf(
        "es" to "Guardar",
        "en" to "Save",
        "pt" to "Salvar"
    )

    val buttonBackToList = mapOf(
        "es" to "Volver a la lista",
        "en" to "Back to list",
        "pt" to "Voltar à lista"
    )

    val errorFieldsEmpty = mapOf(
        "es" to "Por favor, complete los campos obligatorios.",
        "en" to "Please fill in the required fields.",
        "pt" to "Por favor, preencha os campos obrigatórios."
    )

    val successWordSaved = mapOf(
        "es" to "Palabra guardada correctamente.",
        "en" to "Word saved successfully.",
        "pt" to "Palavra salva com sucesso."
    )

    // Ejemplo para la pantalla de editar palabra (EditWordScreen)
    val editWordTitle = mapOf(
        "es" to "Editar palabra",
        "en" to "Edit word",
        "pt" to "Editar palavra"
    )

    val successWordUpdated = mapOf(
        "es" to "Cambios guardados correctamente.",
        "en" to "Changes saved successfully.",
        "pt" to "Alterações salvas com sucesso."
    )

    // Puedes seguir añadiendo más textos según las necesidades de tu app...
}

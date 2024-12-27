package com.fagir.fullytrilingual.data.local.seed

import com.fagir.fullytrilingual.data.local.entities.Word

/**
 * Esta clase u objeto se encarga de proveer los registros precargados
 * que deseas insertar en la base de datos.
 */
object PrepopulateData {

    /**
     * Retorna una lista de objetos Word precargados (20 registros),
     * ajustados a la nueva estructura:
     *
     *   wordEs, wordEn, wordPt
     *   phraseEs, phraseEn, phrasePt
     */
    fun getPrepopulatedWords(): List<Word> {
        return listOf(
            // 1) baseLanguage = "es"
            Word(
                id = 1,
                wordEs = "Hola",
                wordEn = "Hello",
                wordPt = "Olá",
                phraseEs = "Hola, amigos",
                phraseEn = "Hello, friends",
                phrasePt = "Olá, amigos"
            ),
            // 2) baseLanguage = "es"
            Word(
                id = 2,
                wordEs = "Perro",
                wordEn = "Dog",
                wordPt = "Cachorro",
                phraseEs = "El perro corre",
                phraseEn = "The dog runs",
                phrasePt = "O cachorro corre"
            ),
            // 3) baseLanguage = "es"
            Word(
                id = 3,
                wordEs = "Libro",
                wordEn = "Book",
                wordPt = "Livro",
                phraseEs = "El libro es nuevo",
                phraseEn = "The book is new",
                phrasePt = "O livro é novo"
            ),
            // 4) baseLanguage = "es"
            Word(
                id = 4,
                wordEs = "Gato",
                wordEn = "Cat",
                wordPt = "Gato",
                phraseEs = "El gato duerme",
                phraseEn = "The cat sleeps",
                phrasePt = "O gato dorme"
            ),
            // 5) baseLanguage = "en"
            Word(
                id = 5,
                wordEn = "House",
                wordEs = "Casa",
                wordPt = "Casa",
                phraseEn = "The house is big",
                phraseEs = "La casa es grande",
                phrasePt = "A casa é grande"
            ),
            // 6) baseLanguage = "en"
            Word(
                id = 6,
                wordEn = "Water",
                wordEs = "Agua",
                wordPt = "Água",
                phraseEn = "Water is essential",
                phraseEs = "El agua es esencial",
                phrasePt = "A água é esencial"
            ),
            // 7) baseLanguage = "en"
            Word(
                id = 7,
                wordEn = "Car",
                wordEs = "Coche",
                wordPt = "Carro",
                phraseEn = "I drive the car",
                phraseEs = "Conduzco el coche",
                phrasePt = "Eu dirijo o carro"
            ),
            // 8) baseLanguage = "en"
            Word(
                id = 8,
                wordEn = "Food",
                wordEs = "Comida",
                wordPt = "Comida",
                phraseEn = "Food is good",
                phraseEs = "La comida es buena",
                phrasePt = "A comida é boa"
            ),
            // 9) baseLanguage = "pt"
            Word(
                id = 9,
                wordPt = "Livro",
                wordEs = "Libro",
                wordEn = "Book",
                phrasePt = "O livro é interessante",
                phraseEs = "El libro es interesante",
                phraseEn = "The book is interesting"
            ),
            // 10) baseLanguage = "pt"
            Word(
                id = 10,
                wordPt = "Dia",
                wordEs = "Día",
                wordEn = "Day",
                phrasePt = "O dia está lindo",
                phraseEs = "El día está lindo",
                phraseEn = "The day is beautiful"
            ),
            // 11) baseLanguage = "pt"
            Word(
                id = 11,
                wordPt = "Café",
                wordEs = "Café",
                wordEn = "Coffee",
                phrasePt = "Eu gosto de café",
                phraseEs = "Me gusta el café",
                phraseEn = "I like coffee"
            ),
            // 12) baseLanguage = "es"
            Word(
                id = 12,
                wordEs = "Mundo",
                wordEn = "World",
                wordPt = "Mundo",
                phraseEs = "El mundo es grande",
                phraseEn = "The world is big",
                phrasePt = "O mundo é grande"
            ),
            // 13) baseLanguage = "es"
            Word(
                id = 13,
                wordEs = "Ciudad",
                wordEn = "City",
                wordPt = "Cidade",
                phraseEs = "La ciudad es ruidosa",
                phraseEn = "The city is noisy",
                phrasePt = "A cidade é barulhenta"
            ),
            // 14) baseLanguage = "es"
            Word(
                id = 14,
                wordEs = "Niño",
                wordEn = "Child",
                wordPt = "Criança",
                phraseEs = "El niño juega",
                phraseEn = "The child plays",
                phrasePt = "A criança brinca"
            ),
            // 15) baseLanguage = "en"
            Word(
                id = 15,
                wordEn = "Sun",
                wordEs = "Sol",
                wordPt = "Sol",
                phraseEn = "The sun is bright",
                phraseEs = "El sol es brillante",
                phrasePt = "O sol é brilhante"
            ),
            // 16) baseLanguage = "en"
            Word(
                id = 16,
                wordEn = "Dog",
                wordEs = "Perro",
                wordPt = "Cachorro",
                phraseEn = "I love my dog",
                phraseEs = "Amo a mi perro",
                phrasePt = "Amo meu cachorro"
            ),
            // 17) baseLanguage = "pt"
            Word(
                id = 17,
                wordPt = "Carro",
                wordEs = "Coche",
                wordEn = "Car",
                phrasePt = "Eu tenho um carro",
                phraseEs = "Tengo un coche",
                phraseEn = "I have a car"
            ),
            // 18) baseLanguage = "pt"
            Word(
                id = 18,
                wordPt = "Casa",
                wordEs = "Casa",
                wordEn = "House",
                phrasePt = "Minha casa é confortável",
                phraseEs = "Mi casa es cómoda",
                phraseEn = "My house is comfortable"
            ),
            // 19) baseLanguage = "en"
            Word(
                id = 19,
                wordEn = "Book",
                wordEs = "Libro",
                wordPt = "Livro",
                phraseEn = "I read the book",
                phraseEs = "Leo el libro",
                phrasePt = "Eu leio o livro"
            ),
            // 20) baseLanguage = "es"
            Word(
                id = 20,
                wordEs = "Flor",
                wordEn = "Flower",
                wordPt = "Flor",
                phraseEs = "La flor es bonita",
                phraseEn = "The flower is pretty",
                phrasePt = "A flor é bonita"
            )
        )
    }
}

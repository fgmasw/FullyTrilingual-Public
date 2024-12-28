package com.fagir.fullytrilingual.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.fagir.fullytrilingual.data.local.dao.WordDao
import com.fagir.fullytrilingual.data.local.entities.Word
import com.fagir.fullytrilingual.data.local.seed.PrepopulateData
import java.util.concurrent.Executors
import kotlinx.coroutines.runBlocking

@Database(entities = [Word::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    // Aquí definimos nuestra interfaz para acceder a WordDao.
    abstract fun wordDao(): WordDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        // Migración de la versión 2 a la 3.
        // Esto ajusta la estructura de la tabla para las nuevas columnas ES, EN, PT.
        private val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // 1) Creamos la nueva tabla con los campos correctos.
                database.execSQL(
                    """
                    CREATE TABLE IF NOT EXISTS `words_new` (
                        `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        `wordEs` TEXT NOT NULL,
                        `wordEn` TEXT NOT NULL,
                        `wordPt` TEXT NOT NULL,
                        `phraseEs` TEXT NOT NULL,
                        `phraseEn` TEXT NOT NULL,
                        `phrasePt` TEXT NOT NULL
                    )
                    """.trimIndent()
                )

                // 2) Copiamos los datos de la tabla vieja a la nueva.
                //    Ajustamos el SELECT según cómo estaban antes los campos.
                database.execSQL(
                    """
                    INSERT INTO `words_new` (
                        `id`,
                        `wordEs`,
                        `wordEn`,
                        `wordPt`,
                        `phraseEs`,
                        `phraseEn`,
                        `phrasePt`
                    )
                    SELECT
                        `id`,
                        `word` AS `wordEs`,
                        `translation1` AS `wordEn`,
                        `translation2` AS `wordPt`,
                        `phraseBase` AS `phraseEs`,
                        `phraseTranslation1` AS `phraseEn`,
                        `phraseTranslation2` AS `phrasePt`
                    FROM `words`
                    """.trimIndent()
                )

                // 3) Borramos la tabla vieja y renombramos la nueva.
                database.execSQL("DROP TABLE `words`")
                database.execSQL("ALTER TABLE `words_new` RENAME TO `words`")
            }
        }

        // Migración de la versión 1 a 2 (si hace falta).
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Aquí irían los pasos para migrar de la versión 1 a la 2.
            }
        }

        // Callback para cuando se crea la base de datos por primera vez.
        // Pre-poblamos la tabla con datos iniciales.
        private val appDatabaseCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                Executors.newSingleThreadExecutor().execute {
                    INSTANCE?.let { database ->
                        val dao = database.wordDao()
                        PrepopulateData.getPrepopulatedWords().forEach { word ->
                            runBlocking {
                                dao.insertWord(word)
                            }
                        }
                    }
                }
            }
        }

        // Esta función devuelve la instancia de AppDatabase.
        // Usa un bloqueo para que sea segura en hilos múltiples.
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "words_database"
                )
                    // Agregamos las migraciones necesarias.
                    .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                    .addCallback(appDatabaseCallback)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

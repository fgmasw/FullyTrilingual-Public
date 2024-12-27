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

    abstract fun wordDao(): WordDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        // Migración de la versión 2 a la versión 3
        private val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // 1) Crear nueva tabla con columnas ES, EN, PT
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

                // 2) Copiar los datos de la tabla antigua (versión 2) a la nueva
                //    Suponiendo que en la versión 2 tenías:
                //    - word = Español
                //    - translation1 = Inglés
                //    - translation2 = Portugués
                //    - phraseBase = Español
                //    - phraseTranslation1 = Inglés
                //    - phraseTranslation2 = Portugués
                //    Si tus datos estaban mapeados distinto, ajusta el SELECT.
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

                // 3) Eliminar tabla antigua y renombrar
                database.execSQL("DROP TABLE `words`")
                database.execSQL("ALTER TABLE `words_new` RENAME TO `words`")
            }
        }

        // Migración previa (1 a 2) si aún la necesitas
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Tu migración anterior...
            }
        }

        private val appDatabaseCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                Executors.newSingleThreadExecutor().execute {
                    INSTANCE?.let { database ->
                        val dao = database.wordDao()
                        // Pre-poblar la BD con datos iniciales
                        PrepopulateData.getPrepopulatedWords().forEach { word ->
                            runBlocking {
                                dao.insertWord(word)
                            }
                        }
                    }
                }
            }
        }

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "words_database"
                )
                    // Incluimos TODAS las migraciones necesarias:
                    .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                    .addCallback(appDatabaseCallback)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

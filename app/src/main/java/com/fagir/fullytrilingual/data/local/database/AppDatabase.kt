package com.fagir.fullytrilingual.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.fagir.fullytrilingual.data.local.dao.WordDao
import com.fagir.fullytrilingual.data.local.entities.Word

@Database(entities = [Word::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        // Migración de la versión 1 a la versión 2
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Crear nueva tabla con la estructura actualizada
                database.execSQL("""
                    CREATE TABLE IF NOT EXISTS `words_new` (
                        `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        `baseLanguage` TEXT NOT NULL,
                        `word` TEXT NOT NULL,
                        `translation1` TEXT NOT NULL,
                        `translation2` TEXT NOT NULL,
                        `phraseBase` TEXT NOT NULL,
                        `phraseTranslation1` TEXT NOT NULL,
                        `phraseTranslation2` TEXT NOT NULL
                    )
                """.trimIndent())

                // Migrar datos de la tabla antigua (un idioma) a la nueva estructura
                database.execSQL("""
                    INSERT INTO `words_new` (`id`, `baseLanguage`, `word`, `translation1`, `translation2`, `phraseBase`, `phraseTranslation1`, `phraseTranslation2`)
                    SELECT 
                        `id`, 
                        `baseLanguage`, 
                        `word`, 
                        '' AS `translation1`, 
                        '' AS `translation2`, 
                        `phraseBase`, 
                        '' AS `phraseTranslation1`, 
                        '' AS `phraseTranslation2`
                    FROM `words`
                """.trimIndent())

                // Eliminar la tabla antigua
                database.execSQL("DROP TABLE `words`")

                // Renombrar la nueva tabla para mantener el nombre original
                database.execSQL("ALTER TABLE `words_new` RENAME TO `words`")
            }
        }

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "words_database"
                )
                    .addMigrations(MIGRATION_1_2) // Agregar la migración
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

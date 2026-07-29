package com.importantdays.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.importantdays.data.dao.DayDao
import com.importantdays.data.entity.DayEntity

import com.importantdays.data.dao.NoteDao
import com.importantdays.data.entity.NoteEntity

import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.migration.Migration

@Database(entities = [DayEntity::class, NoteEntity::class], version = 7, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dayDao(): DayDao
    abstract fun noteDao(): NoteDao

    companion object {
        val MIGRATION_4_5 = object : Migration(4, 5) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE notes ADD COLUMN color INTEGER NOT NULL DEFAULT 0")
            }
        }
        val MIGRATION_5_6 = object : Migration(5, 6) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("CREATE TABLE notes_new (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, title TEXT NOT NULL, content TEXT NOT NULL, timestamp INTEGER NOT NULL)")
                db.execSQL("INSERT INTO notes_new (id, title, content, timestamp) SELECT id, title, content, timestamp FROM notes")
                db.execSQL("DROP TABLE notes")
                db.execSQL("ALTER TABLE notes_new RENAME TO notes")
            }
        }
        val MIGRATION_6_7 = object : Migration(6, 7) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE notes ADD COLUMN reminderTimestamp INTEGER DEFAULT NULL")
            }
        }
    }
}

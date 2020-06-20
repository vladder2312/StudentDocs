package com.vladder2312.studentdocs.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vladder2312.studentdocs.data.db.entities.*

/**
 * Абстрактный класс базы данных
 */
@Database(
    entities = [
        DocumentEntity::class,
        PhotoEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class DocDatabase : RoomDatabase() {
    abstract val docDao : DocDao
}
package com.devoTeam.harrypotter.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.devoTeam.harrypotter.domain.model.Book

@Database(entities = [Book::class], version = 2, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun cartDao() : CartDao

    companion object{
        const val DATABASE_NAME : String = "ROOM_DB"
    }
}
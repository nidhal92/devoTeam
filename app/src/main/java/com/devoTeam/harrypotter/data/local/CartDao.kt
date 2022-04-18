package com.devoTeam.harrypotter.data.local

import androidx.room.*
import com.devoTeam.harrypotter.domain.model.Book
@Dao
interface CartDao {
    @Query("SELECT * FROM itemCart ")
    fun getAllPosts(): List<Book>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveItem(item: Book)

    @Delete
    fun delete(item: Book)

    @Query("DELETE FROM itemCart")
    fun deleteAll()
    @Query("SELECT COUNT(*) FROM itemCart")
    fun getItemsCount():Int
}
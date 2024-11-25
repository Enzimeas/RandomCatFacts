package com.example.randomcatfacts.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Delete
import androidx.room.Query
import androidx.lifecycle.LiveData

@Dao
interface CatDao {

    @Query("SELECT * FROM Cat")
    fun getAllCats(): LiveData<List<Cat>>

    @Insert
    suspend fun insertCat(cat: Cat)

    @Delete
    suspend fun deleteCat(cat: Cat)
}

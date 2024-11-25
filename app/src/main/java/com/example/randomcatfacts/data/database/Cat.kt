package com.example.randomcatfacts.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Cat")
data class Cat(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val photoUrl: String
)


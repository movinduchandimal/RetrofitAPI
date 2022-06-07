package com.example.myapplication03.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PhotoDao {
    @Query("SELECT * FROM Photo")
    fun getAllPhotoFromDB():List<Photo>

    @Insert
    fun insertPhoto(vararg photo: Photo)
}
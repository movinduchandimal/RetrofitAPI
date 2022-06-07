package com.example.myapplication03.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Photo (
        var albumId: Int,
        @PrimaryKey var id: Int,
        var title: String,
        var url: String,
        var thumbnailUrl: String
        )
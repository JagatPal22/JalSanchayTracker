package com.example.jalsanchaytracker.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rainfall_records")
data class RainfallRecord(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val rainfallMm: Float,

    val litersSaved: Float,

    val date: String
)
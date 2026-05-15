package com.example.jalsanchaytracker.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [RainfallRecord::class],
    version = 1
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun rainfallDao(): RainfallDao
}
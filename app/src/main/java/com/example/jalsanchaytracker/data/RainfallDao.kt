package com.example.jalsanchaytracker.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RainfallDao {

    @Insert
    suspend fun insertRecord(record: RainfallRecord)

    @Query("SELECT * FROM rainfall_records ORDER BY id DESC")
    fun getAllRecords(): Flow<List<RainfallRecord>>
}
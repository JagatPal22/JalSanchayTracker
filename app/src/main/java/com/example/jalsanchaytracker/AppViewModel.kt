package com.example.jalsanchaytracker

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jalsanchaytracker.data.RainfallDao
import com.example.jalsanchaytracker.data.RainfallRecord
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
class AppViewModel(

    private val dao: RainfallDao,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    // Persistent setup values
    var roofArea = 0f

    var tankCapacity = 0f

    // Rainfall history list
    val rainfallHistory = mutableStateListOf<RainfallRecord>()

    init {

        // Load RoomDB records
        viewModelScope.launch {

            dao.getAllRecords().collectLatest { records ->

                rainfallHistory.clear()

                rainfallHistory.addAll(records)
            }
        }

        // Load saved roof area from DataStore
        viewModelScope.launch {

            dataStoreManager.roofAreaFlow.collectLatest {

                roofArea = it
            }
        }

        // Load saved tank capacity from DataStore
        viewModelScope.launch {

            dataStoreManager.tankCapacityFlow.collectLatest {

                tankCapacity = it
            }
        }
    }

    // Save roof area permanently
    fun saveRoofArea(value: Float) {

        roofArea = value

        viewModelScope.launch {

            dataStoreManager.saveRoofArea(value)
        }
    }

    // Save tank capacity permanently
    fun saveTankCapacity(value: Float) {

        tankCapacity = value

        viewModelScope.launch {

            dataStoreManager.saveTankCapacity(value)
        }
    }

    // Main rainfall calculation formula
    fun calculateLiters(rainfallMm: Float): Float {

        return roofArea * rainfallMm * 0.0929f * 0.8f
    }

    // Save rainfall record into RoomDB
    fun addRainfall(mm: Float) {

        val liters = calculateLiters(mm)

        viewModelScope.launch {

            dao.insertRecord(

                RainfallRecord(
                    rainfallMm = mm,
                    litersSaved = liters,
                    date = SimpleDateFormat(
                        "yyyy-MM-dd",
                        Locale.getDefault()
                    ).format(Date())
                )
            )
        }
    }

    // Total liters saved
    fun totalSavings(): Float {

        return rainfallHistory.sumOf {
            it.litersSaved.toDouble()
        }.toFloat()
    }

    // Today's latest rainfall saving
    fun savedToday(): Float {

        val today = SimpleDateFormat(
            "yyyy-MM-dd",
            Locale.getDefault()
        ).format(Date())

        return rainfallHistory.filter {

            it.date == today

        }.sumOf {

            it.litersSaved.toDouble()

        }.toFloat()
    }

    // Household water days estimation
    fun waterDays(): Int {

        return (totalSavings() / 150f).toInt()
    }

    // Tank fill percentage
    fun tankLevel(): Float {

        if (tankCapacity == 0f) return 0f

        return (totalSavings() / tankCapacity).coerceAtMost(1f)
    }
}
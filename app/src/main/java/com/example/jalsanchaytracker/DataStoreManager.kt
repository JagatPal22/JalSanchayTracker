package com.example.jalsanchaytracker

import android.content.Context
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("settings")

class DataStoreManager(
    private val context: Context
) {

    companion object {

        val ROOF_AREA = floatPreferencesKey("roof_area")

        val TANK_CAPACITY = floatPreferencesKey("tank_capacity")
    }

    suspend fun saveRoofArea(value: Float) {

        context.dataStore.edit {

            it[ROOF_AREA] = value
        }
    }

    suspend fun saveTankCapacity(value: Float) {

        context.dataStore.edit {

            it[TANK_CAPACITY] = value
        }
    }

    val roofAreaFlow = context.dataStore.data.map {

        it[ROOF_AREA] ?: 0f
    }

    val tankCapacityFlow = context.dataStore.data.map {

        it[TANK_CAPACITY] ?: 0f
    }
}
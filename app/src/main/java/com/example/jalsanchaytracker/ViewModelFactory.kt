package com.example.jalsanchaytracker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jalsanchaytracker.data.RainfallDao

class ViewModelFactory(
    private val dao: RainfallDao,
    private val dataStoreManager: DataStoreManager
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return AppViewModel(dao, dataStoreManager) as T
    }
}
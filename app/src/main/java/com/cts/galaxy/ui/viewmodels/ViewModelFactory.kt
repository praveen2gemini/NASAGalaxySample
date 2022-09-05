package com.cts.galaxy.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cts.galaxy.api.ApiHelper
import com.cts.galaxy.api.repository.GalaxyRepositoryImpl
import com.cts.galaxy.api.usecase.GalaxyUseCase

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GalaxyCollectionViewModel::class.java)) {
            return GalaxyCollectionViewModel(GalaxyUseCase(GalaxyRepositoryImpl(apiHelper))) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}
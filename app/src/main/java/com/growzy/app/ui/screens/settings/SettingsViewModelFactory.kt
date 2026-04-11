package com.growzy.app.ui.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.growzy.app.domain.preferences.AppPreferences
import com.growzy.app.domain.repository.FundRepository

class SettingsViewModelFactory(
    private val appPreferences: AppPreferences,
    private val fundRepository: FundRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SettingsViewModel::class.java)) {
            return SettingsViewModel(appPreferences, fundRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

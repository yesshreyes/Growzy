package com.growzy.app.ui.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.growzy.app.domain.preferences.AppPreferences
import com.growzy.app.domain.preferences.ThemeMode
import com.growzy.app.domain.repository.FundRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val appPreferences: AppPreferences,
    private val fundRepository: FundRepository
) : ViewModel() {

    val themeMode: StateFlow<ThemeMode> = appPreferences.themeMode.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ThemeMode.SYSTEM
    )

    fun setThemeMode(mode: ThemeMode) {
        viewModelScope.launch {
            appPreferences.setThemeMode(mode)
        }
    }

    fun clearCache(onComplete: () -> Unit) {
        viewModelScope.launch {
            fundRepository.clearCache()
            onComplete()
        }
    }
}

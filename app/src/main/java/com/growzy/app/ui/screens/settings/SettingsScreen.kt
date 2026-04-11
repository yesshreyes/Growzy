package com.growzy.app.ui.screens.settings

import android.widget.Toast
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.growzy.app.GrowzyApp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen() {

    val context = LocalContext.current
    val app = context.applicationContext as GrowzyApp

    val viewModel: SettingsViewModel = viewModel(
        factory = SettingsViewModelFactory(
            app.container.appPreferences,
            app.container.fundRepository
        )
    )

    val currentTheme by viewModel.themeMode.collectAsState()

    SettingsContent(
        currentTheme = currentTheme,
        onThemeChange = { mode ->
            viewModel.setThemeMode(mode)
        },
        onClearCacheClick = {
            viewModel.clearCache {
                Toast.makeText(context, "Cache Cleared", Toast.LENGTH_SHORT).show()
            }
        }
    )
}

package com.example.installerxui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.installerxui.ui.screen.PreferencesScreen
import com.example.installerxui.ui.theme.InstallerXTheme

class MingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InstallerXTheme {
                PreferencesScreen()
            }
        }
    }
}

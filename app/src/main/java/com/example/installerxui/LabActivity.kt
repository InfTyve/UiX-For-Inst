package com.example.installerxui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.installerxui.ui.screen.LabPage
import com.example.installerxui.ui.theme.InstallerXTheme

class LabActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InstallerXTheme {
                LabPage()
            }
        }
    }
}

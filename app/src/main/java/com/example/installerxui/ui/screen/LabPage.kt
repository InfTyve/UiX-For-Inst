package com.example.installerxui.ui.screen

import androidx.activity.compose.LocalActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.installerxui.ui.widget.InfoTipCard
import com.example.installerxui.ui.widget.SwitchWidget
import com.example.installerxui.ui.widget.setting.DropDownMenuWidget
import com.example.installerxui.ui.widget.setting.ExpressiveBackButton

/** Root 实现（管理器）选项 */
enum class RootImplementation(val label: String) {
    Magisk("Magisk"),
    KernelSU("KernelSU"),
    APatch("APatch"),
}

/**
 * 实验室页（简化版）：
 * 仅保留「模块刷写」开关，开启后显示「Root 实现」选择（Magisk/KernelSU/APatch）。
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LabPage() {
    val activity = LocalActivity.current
    var moduleFlashEnabled by remember { mutableStateOf(false) }
    var rootImplementation by remember { mutableStateOf(RootImplementation.Magisk) }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        topBar = {
            TopAppBar(
                title = { Text("实验室") },
                navigationIcon = {
                    ExpressiveBackButton(
                        onClick = { activity?.finish() },
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer,
                ),
            )
        },
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        ) {
            item {
                InfoTipCard("实验性功能，可能不稳定。")
            }
            item {
                // 参照 PreferencesScreen.ExpandableCard：Card + AnimatedVisibility，
                // 避免原版 SegmentedColumn.expandableItem 在 Android 12 的动态插删闪退
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceBright,
                    ),
                ) {
                    Column(modifier = Modifier.padding(vertical = 4.dp)) {
                        SwitchWidget(
                            icon = Icons.Filled.Build,
                            title = "模块刷写",
                            description = "开启后选择 Root 实现",
                            checked = moduleFlashEnabled,
                            onCheckedChange = { moduleFlashEnabled = it },
                        )
                        AnimatedVisibility(
                            visible = moduleFlashEnabled,
                            enter = fadeIn() + expandVertically(),
                            exit = fadeOut() + shrinkVertically(),
                        ) {
                            DropDownMenuWidget(
                                icon = Icons.Filled.Settings,
                                title = "Root 实现",
                                description = rootImplementation.label,
                                choice = rootImplementation.ordinal,
                                data = RootImplementation.entries.map { it.label },
                                onChoiceChange = { index ->
                                    rootImplementation = RootImplementation.entries[index]
                                },
                            )
                        }
                    }
                }
            }
        }
    }
}

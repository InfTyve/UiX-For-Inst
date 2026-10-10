package com.example.installerxui.ui.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.installerxui.ui.widget.InfoTipCard
import com.example.installerxui.ui.widget.SegmentedColumn
import com.example.installerxui.ui.widget.SwitchWidget
import com.example.installerxui.ui.widget.setting.DropDownMenuWidget

/** Root 实现（管理器）选项 */
enum class RootImplementation(val label: String) {
    Magisk("Magisk"),
    KernelSU("KernelSU"),
    APatch("APatch"),
}

/**
 * 实验室页内容（不含标题栏）。
 * 标题栏由 LabActivity 按「大标题样式」选择渲染（新版 / 旧版 / 普通顶部栏）。
 */
@Composable
fun LabPageContent() {
    var moduleFlashEnabled by remember { mutableStateOf(false) }
    var rootImplementation by remember { mutableStateOf(RootImplementation.Magisk) }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
    ) {
        item {
            InfoTipCard("实验性功能，可能不稳定。")
        }
        item {
            SegmentedColumn(title = "模块") {
                item {
                    SwitchWidget(
                        icon = Icons.Filled.Build,
                        title = "模块刷写",
                        description = "开启后选择 Root 实现",
                        checked = moduleFlashEnabled,
                        onCheckedChange = { moduleFlashEnabled = it },
                    )
                }
                if (moduleFlashEnabled) {
                    item {
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

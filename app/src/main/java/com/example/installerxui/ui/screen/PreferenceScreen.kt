package com.example.installerxui.ui.screen

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BatteryFull
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.TouchApp
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.filled.Wifi
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.installerxui.ui.widget.setting.ExpressiveBackButton

/**
 * 主页 PreferencesScreen 的复制副本，项目自定义组件统一使用全限定类名引用。
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreferenceScreen() {
    val context = LocalContext.current
    var adbVerify by remember { mutableStateOf(false) }
    var internetAccess by remember { mutableStateOf(true) }
    var ignoreBattery by remember { mutableStateOf(false) }
    var hideLauncherIcon by remember { mutableStateOf(false) }
    var advancedExpanded by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        topBar = {
            TopAppBar(
                title = { Text("偏好设置") },
                navigationIcon = {
                    ExpressiveBackButton(
                        onClick = { (context as? Activity)?.finish() },
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
                com.example.installerxui.ui.widget.StatusCard(
                    appName = "InstallerX UI",
                    version = "Preview 1.0.0 (1)",
                    useBlur = true,
                )
            }
            item {
                com.example.installerxui.ui.widget.InfoTipCard("这是复刻 InstallerX 风格的提示卡，用于展示须知信息。")
            }

            item {
                com.example.installerxui.ui.widget.SegmentedColumn(title = "个性化") {
                    item {
                        com.example.installerxui.ui.widget.NavigationItemWidget(
                            icon = Icons.Filled.Palette,
                            title = "主题设置",
                            description = "深色 / 浅色 / 跟随系统",
                            onClick = {},
                        )
                    }
                    item {
                        com.example.installerxui.ui.widget.NavigationItemWidget(
                            icon = Icons.Filled.Settings,
                            title = "安装器设置",
                            description = "安装模式、签名等",
                            onClick = {},
                        )
                    }
                    item {
                        com.example.installerxui.ui.widget.NavigationItemWidget(
                            icon = Icons.Filled.Delete,
                            title = "卸载器设置",
                            description = "卸载选项",
                            onClick = {},
                        )
                    }
                }
            }

            item {
                com.example.installerxui.ui.widget.SegmentedColumn(title = "基本") {
                    item {
                        com.example.installerxui.ui.widget.SwitchWidget(
                            icon = Icons.Filled.Wifi,
                            title = "网络设置",
                            description = "允许联网下载资源",
                            checked = internetAccess,
                            onCheckedChange = { internetAccess = it },
                        )
                    }
                    item {
                        com.example.installerxui.ui.widget.SwitchWidget(
                            icon = Icons.Filled.Shield,
                            title = "停用 ADB 安装验证",
                            description = "禁止安装扫描（Play 保护机制等）",
                            checked = adbVerify,
                            onCheckedChange = { adbVerify = it },
                        )
                    }
                    item {
                        com.example.installerxui.ui.widget.SwitchWidget(
                            icon = Icons.Filled.BatteryFull,
                            title = "忽略电池优化",
                            description = "后台安装不被系统限制",
                            checked = ignoreBattery,
                            onCheckedChange = { ignoreBattery = it },
                        )
                    }
                    item {
                        com.example.installerxui.ui.widget.SwitchWidget(
                            icon = Icons.Filled.VisibilityOff,
                            title = "隐藏启动器图标",
                            description = "从桌面隐藏应用入口",
                            checked = hideLauncherIcon,
                            onCheckedChange = { hideLauncherIcon = it },
                        )
                    }
                }
            }

            item {
                com.example.installerxui.ui.widget.SegmentedColumn(title = "动画演示") {
                    expandableItem(
                        expanded = advancedExpanded,
                        topContent = {
                            com.example.installerxui.ui.widget.NavigationItemWidget(
                                icon = Icons.Filled.Build,
                                title = "高级选项",
                                description = "点击展开 / 收起，圆角平滑过渡",
                                onClick = { advancedExpanded = !advancedExpanded },
                            )
                        },
                        bottomContent = {
                            com.example.installerxui.ui.widget.BaseWidget(
                                icon = Icons.Filled.Build,
                                title = "展开的子项",
                                description = "这是展开后显示的内容",
                            )
                        },
                    )
                }
            }

            item {
                HighlightableCard()
            }
        }
    }
}

/**
 * 演示 InteractiveHighlight：点按时出现 AGSL 水波高亮。
 */
@Composable
private fun HighlightableCard() {
    val scope = rememberCoroutineScope()
    // RuntimeShader 需要 API 33（Android 13），低版本退化为普通卡片、无高亮
    val highlight = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        remember(scope) { com.example.installerxui.ui.animation.InteractiveHighlight(scope) }
    } else {
        null
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .then(highlight?.gestureModifier ?: Modifier)
            .then(highlight?.modifier ?: Modifier),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Icon(
                imageVector = Icons.Filled.TouchApp,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
            )
            Column {
                Text(
                    text = "点按水波高亮",
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    text = "按住并拖动可看到 RuntimeShader 高亮跟随手指",
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}

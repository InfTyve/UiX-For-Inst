package com.example.installerxui.ui.widget.setting

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.compose.ui.platform.ComposeView
import com.example.installerxui.R
import com.example.installerxui.ui.theme.InstallerXTheme
import com.google.android.material.appbar.CollapsingToolbarLayout

/**
 * CollapsingToolbarLayout 的自定义子类：
 * 统一承载 Compose 的 ExpressiveBackButton，并提供开关控制返回按钮显示/隐藏。
 *
 * 用法（XmlPreferenceActivity）：
 * ```
 * findViewById<ExpressiveCollapsingToolbarLayout>(R.id.collapsing_toolbar)
 *     .setBackButtonEnabled(true)   // true 开启返回按钮，false 关闭返回按钮
 * ```
 */
class ExpressiveCollapsingToolbarLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : CollapsingToolbarLayout(context, attrs, defStyleAttr) {

    private var backButtonContainer: ComposeView? = null

    override fun onFinishInflate() {
        super.onFinishInflate()
        backButtonContainer = findViewById(R.id.back_button_container)
        backButtonContainer?.setContent {
            InstallerXTheme {
                ExpressiveBackButton(onClick = { (context as? Activity)?.finish() })
            }
        }
    }

    /** true 开启返回按钮，false 关闭返回按钮（隐藏按钮并禁用点击） */
    fun setBackButtonEnabled(enabled: Boolean) {
        backButtonContainer?.visibility = if (enabled) View.VISIBLE else View.GONE
    }
}

package com.example.installerxui

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.compose.ui.platform.ComposeView
import com.example.installerxui.ui.theme.InstallerXTheme
import com.example.installerxui.ui.widget.setting.ExpressiveBackButton
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout

/**
 * 模仿 Android 12 Settings 的“大标题”基类（Kotlin 版）。
 *
 * 头部（AppBarLayout + CollapsingToolbarLayout）始终浮在内容上方，
 * 并通过 canDrag() = false 关闭随列表滚动自动折叠的行为，
 * 展开/收起由子类通过 [setToolbarExpanded] 主动控制（例如跳转二级页时收起）。
 *
 * 返回按钮使用 Compose 的 ExpressiveBackButton（替代系统返回箭头，保留大标题）。
 */
abstract class CollapsingToolbarBaseActivity : AppCompatActivity() {

    private var contentFrame: FrameLayout? = null
    private var collapsingToolbarLayout: CollapsingToolbarLayout? = null
    private var appBarLayout: AppBarLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 先加载头部布局本身，子类再把内容放进 content_frame
        super.setContentView(R.layout.activity_collapsing_toolbar)

        contentFrame = findViewById(R.id.content_frame)
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar)
        appBarLayout = findViewById(R.id.app_bar)

        val toolbar = findViewById<Toolbar>(R.id.action_bar)
        setSupportActionBar(toolbar)

        // 用 Compose 的 ExpressiveBackButton 替代系统返回箭头（保留 CollapsingToolbar 大标题）
        findViewById<ComposeView>(R.id.back_button_container).setContent {
            InstallerXTheme {
                ExpressiveBackButton(onClick = { finish() })
            }
        }

        appBarLayout?.let { disableAutoCollapse(it) }
    }

    /** 子类调用 setContentView 时，把内容放到 content_frame，头部保持不变 */
    override fun setContentView(layoutResID: Int) {
        contentFrame?.let { frame ->
            frame.removeAllViews()
            LayoutInflater.from(this).inflate(layoutResID, frame, true)
        }
    }

    /** 设置展开态的大标题 */
    fun setLargeTitle(title: CharSequence) {
        collapsingToolbarLayout?.setTitle(title)
    }

    fun setLargeTitle(titleRes: Int) {
        setLargeTitle(getString(titleRes))
    }

    /** 主动展开 / 收起头部（Android 12 顶层页跳二级页时收起） */
    fun setToolbarExpanded(expanded: Boolean) {
        setToolbarExpanded(expanded, true)
    }

    fun setToolbarExpanded(expanded: Boolean, animate: Boolean) {
        appBarLayout?.setExpanded(expanded, animate)
    }

    private fun disableAutoCollapse(appBar: AppBarLayout) {
        val behavior = AppBarLayout.Behavior()
        behavior.setDragCallback(object : AppBarLayout.Behavior.DragCallback() {
            override fun canDrag(appBarLayout: AppBarLayout): Boolean = false
        })
        val lp = appBar.layoutParams as CoordinatorLayout.LayoutParams
        lp.behavior = behavior
    }
}

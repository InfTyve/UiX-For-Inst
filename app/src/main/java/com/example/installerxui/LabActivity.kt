package com.example.installerxui

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.compose.ui.platform.ComposeView
import com.example.installerxui.ui.screen.LabPageContent
import com.example.installerxui.ui.theme.InstallerXTheme
import com.example.installerxui.ui.widget.setting.BigTitleImpl
import com.example.installerxui.ui.widget.setting.ExpressiveCollapsingToolbarLayout
import com.example.installerxui.ui.widget.setting.ExpressiveCollapsingToolbarLayout_Two
import com.example.installerxui.ui.widget.setting.loadBigTitleImpl
import com.example.installerxui.ui.widget.setting.rootImplementation

class LabActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        rootImplementation = loadBigTitleImpl(this)

        // ONE -> activity_preference（新版大标题）
        // TWO -> activity_preference_two（旧版大标题）
        // ORIGINAL -> activity_preference_original（普通固定顶部栏）
        val layoutRes = when (rootImplementation) {
            BigTitleImpl.ONE -> R.layout.activity_preference
            BigTitleImpl.TWO -> R.layout.activity_preference_two
            BigTitleImpl.ORIGINAL -> R.layout.activity_preference_original
        }
        setContentView(layoutRes)

        val toolbar = findViewById<Toolbar>(R.id.action_bar)
        setSupportActionBar(toolbar)

        // 标题
        when (rootImplementation) {
            BigTitleImpl.ONE -> findViewById<ExpressiveCollapsingToolbarLayout>(R.id.collapsing_toolbar)
                .setTitle(getString(R.string.title_lab))
            BigTitleImpl.TWO -> findViewById<ExpressiveCollapsingToolbarLayout_Two>(R.id.collapsing_toolbar)
                .setTitle(getString(R.string.title_lab))
            BigTitleImpl.ORIGINAL -> supportActionBar?.title = getString(R.string.title_lab)
        }

        // 返回按钮
        when (rootImplementation) {
            BigTitleImpl.ONE -> findViewById<ExpressiveCollapsingToolbarLayout>(R.id.collapsing_toolbar)
                .setBackButtonEnabled(true)
            BigTitleImpl.TWO -> findViewById<ExpressiveCollapsingToolbarLayout_Two>(R.id.collapsing_toolbar)
                .setBackButtonEnabled(true)
            BigTitleImpl.ORIGINAL -> supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        // 实验室内容：LabPageContent 放进 content_frame
        val contentFrame = findViewById<FrameLayout>(R.id.content_frame)
        val composeView = ComposeView(this)
        contentFrame.addView(
            composeView,
            FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT,
            ),
        )
        composeView.setContent {
            InstallerXTheme {
                LabPageContent()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}

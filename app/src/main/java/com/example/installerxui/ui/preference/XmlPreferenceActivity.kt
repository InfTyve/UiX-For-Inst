package com.example.installerxui.ui.preference

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.installerxui.R
import com.example.installerxui.ui.widget.setting.ExpressiveCollapsingToolbarLayout

class XmlPreferenceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preference)
        val toolbar = findViewById<Toolbar>(R.id.action_bar)
        setSupportActionBar(toolbar)

        // 通过 CollapsingToolbarLayout 子类控制返回按钮：true 开启，false 关闭
        findViewById<ExpressiveCollapsingToolbarLayout>(R.id.collapsing_toolbar)
            .setBackButtonEnabled(true)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.content_frame, SettingsFragment())
                .commit()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}

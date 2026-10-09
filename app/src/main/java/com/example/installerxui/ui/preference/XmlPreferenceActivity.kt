package com.example.installerxui.ui.preference

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.installerxui.R

class XmlPreferenceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preference)
        val toolbar = findViewById<Toolbar>(R.id.action_bar)
        setSupportActionBar(toolbar)

        // 显示返回箭头
        if (getSupportActionBar() != null) {
            getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
            getSupportActionBar()?.setHomeButtonEnabled(true)
        }
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

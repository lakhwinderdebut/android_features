package com.debut.androidfeatures

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_click_me.setOnClickListener {
            val intent = Intent(this, ActivityWorkManager::class.java)
            startActivity(intent)
        }

        btn_setting_panel.setOnClickListener {
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }

        btn_dark_theme.setOnClickListener {
            val intent=Intent(this,NightThemeActivity::class.java)
            startActivity(intent)
        }
    }
}

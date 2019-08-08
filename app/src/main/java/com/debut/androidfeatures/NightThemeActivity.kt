package com.debut.androidfeatures

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.android.synthetic.main.activity_dark_theme.*


/**
 *  Created by Shivam on 08/August/19.
 *  Dark theme module
 */
class NightThemeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //If night theme is enabled show night theme else show light theme
        //An AppCompatDelegate is a class represents a delegate which you can use to extend AppCompat’s support to any Activity.
        if (InitApplication.getInstance().isNightModeEnabled) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        setContentView(R.layout.activity_dark_theme)

        button.setOnClickListener {
            AlertDialog.Builder(this, R.style.MyDialog)
                .setTitle("Title")
                .setMessage("Message")
                .show()
        }

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
            switchCompat.isChecked = true

        //Enable disable night mode on switch compact button
        switchCompat.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                InitApplication.getInstance().setIsNightModeEnabled(true)
                val intent = intent
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                finish()
                startActivity(intent)

            } else {
                InitApplication.getInstance().setIsNightModeEnabled(false)
                val intent = intent
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                finish()
                startActivity(intent)
            }
        }
    }
}

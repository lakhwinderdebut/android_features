package com.debut.androidfeatures

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_setting.*


/**
 * Setting module will work in APi level 29 i.e in Android Q
 */
class SettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        setListener()
    }

    private fun setListener() {
        btnInternet.setOnClickListener { showInternetSettings() }
        btnVolume.setOnClickListener { showVolumeSettings() }
        btnNFC.setOnClickListener { showNFCSettings() }
    }

    /**
     *  This will open Internet setting
     */
    private fun showInternetSettings() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val intent = Intent(Settings.Panel.ACTION_INTERNET_CONNECTIVITY)
            startActivity(intent)
        }
    }
    /**
     *  This will open Volume setting
     */
    private fun showVolumeSettings() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val intent = Intent(Settings.Panel.ACTION_VOLUME)
            startActivity(intent)
        }
    }
    /**
     *  This will open NFC setting
     */
    private fun showNFCSettings() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            startActivity(Intent(Settings.Panel.ACTION_NFC))
        }
    }
}

package com.dev.android.mywoman.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_splash.*
import android.view.WindowManager
import android.view.View
import com.dev.android.mywoman.R


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val window = window
        val winParams = window.attributes
        winParams.flags = winParams.flags and WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS.inv()
        window.attributes = winParams
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        di_on_boarding.setSelectedPointColor(resources.getColor(R.color.colorTextORIcon))
        vp_on_boarding_container.adapter = SplashAdapter(this@SplashActivity)
        di_on_boarding.setViewPager(vp_on_boarding_container)

    }



}
package com.dev.android.mywoman.splash

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.TypedArray
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.dev.android.mywoman.R
import com.dev.android.mywoman.home.HomeActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_splash.view.*

class SplashAdapter(private val context: Context) : PagerAdapter() {

    private fun getOnBoardingImage(): TypedArray = context.resources.obtainTypedArray(R.array.on_boarding_image)
    private fun getOnBoardingTitle(): Array<String> = context.resources.getStringArray(R.array.on_boarding_title)
    private fun getOnBoardingSubtitle(): Array<String> = context.resources.getStringArray(R.array.on_boarding_subtitle)

    override fun getCount(): Int = getOnBoardingTitle().size

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) { container.removeView(`object` as ConstraintLayout) }

    @SuppressLint("InflateParams")
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val ui = LayoutInflater.from(context).inflate(R.layout.adapter_splash, null)
        container.addView(ui)




        Picasso.get().load(getOnBoardingImage().getResourceId(position, 0)).into(ui.iv_on_boarding_image)
        ui.tv_on_boarding_title.text = getOnBoardingTitle()[position]
        ui.tv_on_boarding_subtitle.text = getOnBoardingSubtitle()[position]

        getOnBoardingImage().recycle()

        ui.tv_on_boarding_title.alpha = 0f
        ui.tv_on_boarding_title.translationY = 500f

        ui.tv_on_boarding_subtitle.alpha = 0f
        ui.tv_on_boarding_subtitle.translationY = 500f

        ui.iv_on_boarding_image.startAnimation(AnimationUtils.loadAnimation(context, R.anim.bounce))
        ui.tv_on_boarding_title.animate().alpha(1f).translationY(0f).setDuration(800).setStartDelay(300).start()
        ui.tv_on_boarding_subtitle.animate().alpha(1f).translationY(0f).setDuration(800).setStartDelay(600).start()

        ui.next_btn.setOnClickListener {
            context.startActivity(Intent(context, HomeActivity::class.java))
        }
        return ui
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object` as ConstraintLayout

}
package com.dev.android.mywoman.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.dev.android.mywoman.R
import com.dev.android.mywoman.models.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val window = window
        val winParams = window.attributes
        winParams.flags = winParams.flags and WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS.inv()
        window.attributes = winParams
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        val product = intent.getParcelableExtra<Product>("product")
        val cat = intent.getStringExtra("category")
        name.text = product?.name
        price.text = product?.price + " $"
        description.text = product.description
        Picasso.get().load(product.image_link).into(product_type)
        rate.rating = product?.rating!!
        category.text = cat

    }
}

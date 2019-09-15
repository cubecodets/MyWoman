package com.dev.android.mywoman.products

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dev.android.mywoman.R
import com.dev.android.mywoman.utils.Resource
import com.dev.android.mywoman.utils.showLoadingDialog
import com.dev.android.mywoman.utils.toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_products_in_type.*
import java.io.IOException

class ProductsInTypeActivity : AppCompatActivity() {

    private lateinit var viewModel: ProductsInTypesViewModel
    private lateinit var dialog: Dialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products_in_type)
        val window = window
        val winParams = window.attributes
        winParams.flags = winParams.flags and WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS.inv()
        window.attributes = winParams
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        viewModel = ViewModelProviders.of(this).get(ProductsInTypesViewModel::class.java)

        val type = intent.getStringExtra("type")
        val image = intent.getIntExtra("image",0)

        Picasso.get().load(image).into(product_type)
         name.text = type


        fetchProducts(type.toLowerCase())
        observeProductsList(type)

    }

    private fun fetchProducts(type : String) {
        viewModel.fetchProducts(type)
    }

    private fun observeProductsList(type: String) {
        viewModel.productsLiveData.observe(this, Observer {
            it.apply {
                when (this) {
                    is Resource.Progress -> {
                        if (loading)
                            dialog = showLoadingDialog()
                    }
                    is Resource.Success -> {
                        dialog.dismiss()
                        rvHorizontal.adapter = ProductsInTypesAdapter(type ,data)
                    }
                    is Resource.Failure -> {
                        if (e is IOException) {
                            toast("Need internet to work!")
                        } else {
                            toast("Error happened try again!")
                        }
                    }
                }
            }
        }


        )
    }
}

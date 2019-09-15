package com.dev.android.mywoman.home

import android.content.Intent
import android.content.res.TypedArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dev.android.mywoman.R
import com.dev.android.mywoman.products.ProductsInTypeActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_product.view.*

class ProductTypesAdapter() : RecyclerView.Adapter<ProductTypesAdapter.ViewHolder>() {



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init { }

        private fun getProductTypesImage(): TypedArray = itemView.context.resources.obtainTypedArray(R.array.product_types_image)
        private fun getProductTypesTitle(): Array<String> =  itemView.context.resources.getStringArray(R.array.product_types_title)
        private fun getProductTypesPrices(): Array<String> =  itemView.context.resources.getStringArray(R.array.product_types_price)

        fun bind() {
            with(itemView) {

                this.setOnClickListener {
                    itemView.context.startActivity(
                        Intent(itemView.context, ProductsInTypeActivity::class.java)
                            .putExtra("type",getProductTypesTitle()[adapterPosition])
                            .putExtra("image",getProductTypesImage().getResourceId(adapterPosition, 0))

                 )
                }
                Picasso.get().load(getProductTypesImage().getResourceId(adapterPosition, 0)).into(image)
                name.text= getProductTypesTitle()[adapterPosition]
                price.text = getProductTypesPrices()[adapterPosition]



            }}}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.row_product, parent, false)
    )

    override fun getItemCount(): Int = 4

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind()
    }



}
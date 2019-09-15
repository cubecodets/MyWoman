package com.dev.android.mywoman.products

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dev.android.mywoman.R
import com.dev.android.mywoman.details.DetailsActivity
import com.dev.android.mywoman.models.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_product.view.*

class ProductsInTypesAdapter(val type : String,val data: List<Product>) : RecyclerView.Adapter<ProductsInTypesAdapter.ViewHolder>() {



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init { }



        fun bind(item : Product) {
            with(itemView) {

                this.setOnClickListener {
                    itemView.context.startActivity(
                        Intent(itemView.context, DetailsActivity::class.java)
                            .putExtra("product",item)
                            .putExtra("category",type)

                    )
                }
                Picasso.get().load(item.image_link).into(image)
                name.text= item.name
                price.text = item.price + " $"



            }}}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.row_product_filters, parent, false)
    )

    override fun getItemCount(): Int = if(data.size>20)20 else data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(data[position])
    }



}
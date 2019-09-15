package com.dev.android.mywoman.products

import com.dev.android.mywoman.models.Product
import com.dev.android.mywoman.reset.Api
import io.reactivex.Single

class ProductsInTypesRepository {

    fun getProducts(product_type: String) : Single<List<Product>> = Api().ProductsByType(product_type)




}
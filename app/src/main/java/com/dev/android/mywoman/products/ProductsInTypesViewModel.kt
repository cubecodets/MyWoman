package com.dev.android.mywoman.products

import androidx.lifecycle.ViewModel

class ProductsInTypesViewModel : ViewModel() {
    private var productsUseCase = ProductsInTypesUseCase()
    var productsLiveData = productsUseCase.resultLiveData()

    fun fetchProducts(product_type : String)  = productsUseCase.getProducts(product_type)

    override fun onCleared() {
        super.onCleared()
        productsUseCase.unSubscribe()
    }
}
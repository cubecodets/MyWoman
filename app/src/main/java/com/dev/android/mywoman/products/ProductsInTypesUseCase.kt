package com.dev.android.mywoman.products

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dev.android.mywoman.models.Product
import com.dev.android.mywoman.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ProductsInTypesUseCase {
    private  var compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val result = MutableLiveData<Resource<List<Product>>>()
    var productRepo = ProductsInTypesRepository()

    fun getProducts(product_type: String) {
        compositeDisposable.add(productRepo.getProducts(product_type)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe
            {
                result.value = Resource.loading(true)
            }
            .subscribe(
                { productsList ->
                    productsList?.let {
                        result.value = Resource.success(productsList)

                    } ?: run {
                        //logic Error (value = null)
                        result.value = Resource.failure(Throwable("no data found!"))
                    }
                },
                { throwable ->
                    Log.e("throwable",""+throwable.printStackTrace())
                    result.value = Resource.failure(throwable)
                }
            )
        )
    }

    fun resultLiveData(): LiveData<Resource<List<Product>>> = result

    fun unSubscribe() {
        compositeDisposable.clear()
    }
}
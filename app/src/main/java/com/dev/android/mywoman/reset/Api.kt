package com.dev.android.mywoman.reset

import com.dev.android.mywoman.constants.Constants
import com.dev.android.mywoman.models.Product
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

private var retrofit: Retrofit? = null

interface Api {


    @GET("/api/v1/products.json" )
    fun ProductsByType(
        @Query("product_type") product_type: String
    ): Single<List<Product>>


    companion object Factory {

        operator fun invoke(): Api {

                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY

                val client = OkHttpClient.Builder()
                    .connectTimeout(1, TimeUnit.MINUTES)
                    .writeTimeout(1, TimeUnit.MINUTES)
                    .readTimeout(1, TimeUnit.MINUTES)
                    .addInterceptor(logging)
                    .build()

                retrofit = Retrofit.Builder()
                    .baseUrl(Constants.baseUrl)
                    .client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()


            return retrofit!!.create(Api::class.java)
        }
    }
}
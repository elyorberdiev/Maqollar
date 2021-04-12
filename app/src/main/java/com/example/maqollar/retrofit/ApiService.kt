package com.example.maqollar.retrofit

import androidx.lifecycle.LiveData
import com.example.maqollar.models.Maqol
import com.example.maqollar.models.Rukn
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("posts")
    fun getAllData(@Query ("page")page:Int):Call<List<Maqol>>

    @GET("categories")
    fun getAllCategories(@Query("per_page")page: Int):Call<List<Rukn>>

    @GET("posts")
    fun getCategoryProverbs(@Query("parent_category")id:Int,
                            @Query("per_page")page: Int):Call<List<Maqol>>

}
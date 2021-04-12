package com.example.maqollar.datasource

import androidx.paging.PageKeyedDataSource
import com.example.maqollar.models.Maqol
import com.example.maqollar.retrofit.ApiClient
import com.example.maqollar.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProverbDataSource:PageKeyedDataSource<Int,Maqol>() {

    val apiService = ApiClient.getRetrofit().create(ApiService::class.java)
    val START_PAGE = 1
    val PAGE_SIZE = 100

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Maqol>
    ) {
        apiService.getAllData(page = START_PAGE).enqueue(object :Callback<List<Maqol>>{
            override fun onResponse(call: Call<List<Maqol>>, response: Response<List<Maqol>>) {
                if (response.isSuccessful) {
                    callback.onResult(response.body()!!, null, START_PAGE + 1)
                }
            }

            override fun onFailure(call: Call<List<Maqol>>, t: Throwable) {

            }

        })

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Maqol>) {

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Maqol>) {

        apiService.getAllData(page = params.key).enqueue(object :Callback<List<Maqol>>{
            override fun onResponse(call: Call<List<Maqol>>, response: Response<List<Maqol>>) {
                if (response.isSuccessful) {
                    val nextKey = if (params.key.toInt() == PAGE_SIZE) null else params.key + 1
                    callback.onResult(response.body()!!, nextKey)
                }
            }

            override fun onFailure(call: Call<List<Maqol>>, t: Throwable) {

            }

        })
    }
}
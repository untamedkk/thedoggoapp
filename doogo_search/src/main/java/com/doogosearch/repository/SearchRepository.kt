package com.doogosearch.repository

import android.util.Log
import com.doogosearch.model.BreedSearch
import com.doogosearch.model.ImageSearch
import com.doogosearch.services.RetrofitClient
import com.doogosearch.services.Services
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object SearchRepository {

    private val TAG = SearchRepository::class.java.simpleName

    private val services: Services by lazy { RetrofitClient.services }

    fun doBreedSearch(query: String, onReceived: OnSearch<BreedSearch>) {
        val call = services.searchByBreedName(query)

        call.enqueue(object : Callback<MutableList<BreedSearch>> {
            override fun onResponse(
                call: Call<MutableList<BreedSearch>>, response: Response<MutableList<BreedSearch>>
            ) {
                response.body()?.let { onReceived.onSuccess(it) }
            }

            override fun onFailure(call: Call<MutableList<BreedSearch>>, t: Throwable) {
                Log.e(TAG, "error while fetching search result ${t.localizedMessage}")
                onReceived.onFailure()
            }

        })
    }

    fun doImageSearch(
        limit: Int,
        page: Int,
        order: ImageSearch.ORDER,
        onReceived: OnSearch<ImageSearch>
    ) {
        val call = services.imageSearch(limit, page, order)

        call.enqueue(object : Callback<MutableList<ImageSearch>> {
            override fun onResponse(
                call: Call<MutableList<ImageSearch>>, response: Response<MutableList<ImageSearch>>
            ) {
                response.body()?.let { onReceived.onSuccess(it) }
            }

            override fun onFailure(call: Call<MutableList<ImageSearch>>, t: Throwable) {
                Log.e(TAG, "error while fetching search result ${t.localizedMessage}")
                onReceived.onFailure()
            }

        })
    }

    interface OnSearch<T> {
        fun onSuccess(data: MutableList<T>)
        fun onFailure()
    }

}
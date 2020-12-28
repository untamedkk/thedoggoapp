package com.doogosearch.services

import com.doogosearch.model.BreedSearch
import com.doogosearch.model.ImageSearch
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Services {

    @GET("breeds/search")
    fun searchByBreedName(@Query("q") q: String): Call<MutableList<BreedSearch>>

    @GET("images/search")
    fun imageSearch(
        @Query("limit") limit: Int,
        @Query("page") page: Int,
        @Query("order") order: ImageSearch.ORDER
    ): Call<MutableList<ImageSearch>>
}
package com.furkan.sisalcase.data.dto

import com.furkan.sisalcase.data.model.ListModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServices {

    @GET("r/{keyword}/top.json")
    suspend fun getData(@Path("keyword") keyword: String): ListModel

}
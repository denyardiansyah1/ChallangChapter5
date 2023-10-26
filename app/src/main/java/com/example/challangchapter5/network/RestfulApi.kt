package com.example.challangchapter5.network

import com.example.challangchapter5.model.CategoryEntity
import com.example.challangchapter5.model.MenuEntity
import retrofit2.Call
import retrofit2.http.GET

interface RestfulApi {
    @GET("listmenu")
    fun getAllMenu(): Call<MenuEntity>
    @GET("category")
    fun  getCategory():Call<CategoryEntity>
}
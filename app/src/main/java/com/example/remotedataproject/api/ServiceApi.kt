package com.example.remotedataproject.api

import com.example.remotedataproject.model.Post
import retrofit2.Call
import retrofit2.http.GET

interface ServiceApi {

    @GET("posts")
    fun getAllPosts() : Call<List<Post>>
}
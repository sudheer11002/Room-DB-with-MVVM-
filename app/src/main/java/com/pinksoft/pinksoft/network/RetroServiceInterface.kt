package com.pinksoft.pinksoft.network

import com.pinksoft.pinksoft.network.model.Comments
import com.pinksoft.pinksoft.network.model.Posts
import com.pinksoft.pinksoft.network.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroServiceInterface {
    @GET("posts")
    fun getPosts(): Call<List<Posts>>

    @GET("users?")
    fun getUser(@Query("id") id: String): Call<List<User>>

    @GET("comments")
    fun getComments(@Query("postId") id: String): Call<List<Comments>>
}
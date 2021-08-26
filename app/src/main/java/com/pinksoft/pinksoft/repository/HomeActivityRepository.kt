package com.pinksoft.pinksoft.repository

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.pinksoft.pinksoft.db.DAO
import com.pinksoft.pinksoft.network.BASE_URL
import com.pinksoft.pinksoft.network.PinkSoftAPI
import com.pinksoft.pinksoft.network.model.Posts
import dagger.Provides
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



class HomeActivityRepository (val application: Application,
                              private val dao: DAO) {
    val showProgress = MutableLiveData<Boolean>()
    val postList = MutableLiveData<List<Posts>>()


    fun changeState() {
        showProgress.value = !(showProgress.value != null && showProgress.value!!)
    }
    fun getAllPostsRecords(): LiveData<List<Posts>> {
        return dao.getAllPostsRecords()
    }

    fun insertPostRecords(posts: Posts) {
        dao.insertPostRecords(posts)
    }


    fun getPostsList(){
        showProgress.value = true
        // Network call
        val retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build()


        val service = retrofit.create(PinkSoftAPI::class.java)

        service.getPosts().enqueue(object  : Callback<List<Posts>> {
            override fun onFailure(call: Call<List<Posts>>, t: Throwable) {
                showProgress.value = false
                Toast.makeText(application,t.message, Toast.LENGTH_SHORT).show()
                Log.d("SearchRepository" , "Response : ${t.message}")
            }

            override fun onResponse(
                call: Call<List<Posts>>,
                response: Response<List<Posts>>
            ) {
                Log.d("SearchRepository" , "Response : ${Gson().toJson(response.body())}")
                postList.value = response.body()
                response.body()?.forEach {
                    insertPostRecords(it)
                }
                showProgress.value = false
            }
        })

    }
}
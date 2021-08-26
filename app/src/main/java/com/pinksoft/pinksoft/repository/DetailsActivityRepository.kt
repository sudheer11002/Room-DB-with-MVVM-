package com.pinksoft.pinksoft.repository

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.pinksoft.pinksoft.network.BASE_URL
import com.pinksoft.pinksoft.network.PinkSoftAPI
import com.pinksoft.pinksoft.network.model.Comments
import com.pinksoft.pinksoft.network.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class DetailsActivityRepository @Inject constructor(val application: Application) {

    val showProgress = MutableLiveData<Boolean>()
    val response = MutableLiveData<List<User>>()
    val commResponse = MutableLiveData<List<Comments>>()

    fun  getUsers(id : String){
        showProgress.value = true

        val retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build()

        val service = retrofit.create(PinkSoftAPI::class.java)
        //___User Data
        service.getUser(id).enqueue(object  : Callback<List<User>>{
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                showProgress.value = false
                Toast.makeText(application,"Error wile accessing the API", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<List<User>>,
                resp: Response<List<User>>
            ) {
                response.value = resp.body()
                showProgress.value = false
                Log.d("userRepository" , "Response : ${Gson().toJson(response)}")

            }
        })
        //__Comments data
            service.getComments(id).enqueue(object  : Callback<List<Comments>>{
                override fun onFailure(call: Call<List<Comments>>, t: Throwable) {
                    showProgress.value = false
                    Toast.makeText(application,"Error wile accessing the API", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<List<Comments>>,
                    resp: Response<List<Comments>>
                ) {
                    commResponse.value = resp.body()
                    showProgress.value = false
                    Log.d("userRepository" , "Response : ${Gson().toJson(commResponse)}")

                }
            })
        }

    }
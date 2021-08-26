package com.pinksoft.pinksoft.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pinksoft.pinksoft.network.model.Comments
import com.pinksoft.pinksoft.network.model.Posts
import com.pinksoft.pinksoft.network.model.User
import com.pinksoft.pinksoft.repository.DetailsActivityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsActivityViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {

    private val repository = DetailsActivityRepository(application)

    val showProgress : LiveData<Boolean>
    val response : LiveData<List<User>>
    val commResponse : LiveData<List<Comments>>

    init {
        this.showProgress = repository.showProgress
        this.response = repository.response
        this.commResponse = repository.commResponse
    }

    fun getUser(getPostId: String){
        repository.getUsers(getPostId)
    }

}
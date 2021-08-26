package com.pinksoft.pinksoft.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.pinksoft.pinksoft.db.DAO
import com.pinksoft.pinksoft.network.PinkSoftAPI
import com.pinksoft.pinksoft.network.model.Posts
import com.pinksoft.pinksoft.repository.HomeActivityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class HomeActivityViewModel (application: Application, dao: DAO) : AndroidViewModel(application) {

    val repository  = HomeActivityRepository(application,dao)

    val showProgress : LiveData<Boolean>
    val postList : LiveData<List<Posts>>

    init {
        this.showProgress = repository.showProgress
        this.postList = repository.postList
    }

    fun changeState(){
        repository.changeState()
    }
    fun getPostsList(){
        repository.getPostsList()
    }


}
package com.pinksoft.pinksoft.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.pinksoft.pinksoft.R
import com.pinksoft.pinksoft.adapter.PostAdapter
import com.pinksoft.pinksoft.network.model.Posts
import com.pinksoft.pinksoft.viewModel.HomeActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject


class HomeActivity : AppCompatActivity(), PostAdapter.CallbackInterface {

    lateinit var viewModel: HomeActivityViewModel
    lateinit var adapter: PostAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        viewModel = ViewModelProvider(this).get(HomeActivityViewModel::class.java)

        viewModel.getPostsList()

        viewModel.showProgress.observe(this, Observer {
            if (it)
                search_progress.visibility = View.VISIBLE
            else
                search_progress.visibility = View.GONE
        })

        viewModel.postList.observe(this, Observer {
            adapter.setPostList(it)
        })
        adapter = PostAdapter(this, this)
        rv_search.adapter = adapter
    }

    override fun passResultCallback(message: Posts) {

        var intent1 = Intent(this@HomeActivity, DetailsActivity::class.java)
        intent1.putExtra("data", message.id.toString())
        startActivity(intent1)
    }
}



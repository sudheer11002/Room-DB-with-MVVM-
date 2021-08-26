package com.pinksoft.pinksoft.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.pinksoft.pinksoft.R
import com.pinksoft.pinksoft.adapter.CommentsAdapter
import com.pinksoft.pinksoft.adapter.PostAdapter
import com.pinksoft.pinksoft.network.model.User
import com.pinksoft.pinksoft.viewModel.DetailsActivityViewModel
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.activity_home.*
import java.lang.Exception


class DetailsActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailsActivityViewModel
    private lateinit var adapter: CommentsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        //view model provider
        viewModel = ViewModelProvider(this).get(DetailsActivityViewModel::class.java)

        val id = intent.getStringExtra("data")
        Log.d("idValue", id.toString())
        if (intent.hasExtra("data")) {

//            tv_name.text = id
            if (id != null) {
                viewModel.getUser(id)
            }
        }
        viewModel.showProgress.observe(this, Observer {
            if (it) {
                search_progressbar.visibility = View.VISIBLE
            }
            else {
                search_progressbar.visibility = View.GONE
                root_View.visibility = View.VISIBLE
            }
        })

        viewModel.response.observe(this, Observer {
            if (it!=null){
                try {
                    tv_name.text = it.get(0).name
                    tv_user_name.text = it.get(0).username
                    tv_email.text = it.get(0).email
                    tv_city.text = it.get(0).address.city
                    tv_phone.text = it.get(0).phone
                } catch (e: Exception) {
                    // handler
                } finally {
                    // optional finally block
                }
            }
        })
        viewModel.commResponse.observe(this, Observer {
            adapter.setCommentsList(it)
        })
        adapter = CommentsAdapter(this)
        rv_comments.adapter = adapter
    }
}
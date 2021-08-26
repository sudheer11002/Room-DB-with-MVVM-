package com.pinksoft.pinksoft.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pinksoft.pinksoft.R
import com.pinksoft.pinksoft.network.model.Posts
import kotlinx.android.synthetic.main.post_view.view.*
import javax.inject.Inject

class PostAdapter @Inject constructor( private val context: Context,public val callbacks: CallbackInterface) :
    RecyclerView.Adapter<PostAdapter.ViewHolder>() {
    private var list: List<Posts> = ArrayList()

    fun setPostList(list: List<Posts>){
        this.list = list
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
        return list.size
    }


    interface CallbackInterface {
        fun passResultCallback(message: Posts)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = list[position].title
        holder.bio.text = list[position].body

        holder.rootView.setOnClickListener {
            callbacks.passResultCallback(list[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.post_view,
                parent,
                false
            )
        )
    }


    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val name = v.title!!
        val bio = v.bio!!
        val rootView = v.child_root!!
    }

}
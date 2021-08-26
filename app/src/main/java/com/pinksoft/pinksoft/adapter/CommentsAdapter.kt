package com.pinksoft.pinksoft.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pinksoft.pinksoft.R
import com.pinksoft.pinksoft.network.model.Comments
import kotlinx.android.synthetic.main.comments_view.view.*

class CommentsAdapter  (private val context: Context) :
    RecyclerView.Adapter<CommentsAdapter.ViewHolder>() {
    private var list: List<Comments> = ArrayList()

    fun setCommentsList(list: List<Comments>){
        this.list = list
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = list[position].name
        holder.email.text = list[position].email
        holder.body.text = list[position].body

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.comments_view,
                parent,
                false
            )
        )
    }


    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val name = v.commenter_name!!
        val email = v.commenter_email!!
        val body = v.commenter_body!!
    }

}
package com.example.simpleinstagram

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PostAdapter(val context: Context, val posts: List<Post>): RecyclerView.Adapter<PostAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAdapter.ViewHolder {
        // Specify layout file to use for this item
        val view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostAdapter.ViewHolder, position: Int) {
        val post = posts.get(position)
        holder.bind(post)
    }

    override fun getItemCount(): Int {
        return posts.size
    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var tvUsername: TextView = itemView.findViewById(R.id.tvUsername)
        var ivImage: ImageView = itemView.findViewById(R.id.ivImage)
        var tvDescription: TextView = itemView.findViewById(R.id.tvDescription)

        fun bind(post: Post){
            tvUsername.text = post.getUser()?.username
            tvDescription.text = post.getDescription()
            Glide.with(itemView.context).load(post.getImage()?.url).into(ivImage)
        }
    }
}
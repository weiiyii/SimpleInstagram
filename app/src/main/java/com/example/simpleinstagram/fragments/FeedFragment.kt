package com.example.simpleinstagram.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.simpleinstagram.Post
import com.example.simpleinstagram.PostAdapter
import com.example.simpleinstagram.R
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FeedFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
open class FeedFragment : Fragment() {

    lateinit var postRecyclerView: RecyclerView
    lateinit var adapter: PostAdapter

    var allPosts: MutableList<Post> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postRecyclerView = view.findViewById(R.id.postRecyclerView)

        adapter = PostAdapter(requireContext(), allPosts)
        postRecyclerView.adapter = adapter
        postRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        queryPosts()

    }

    open fun queryPosts() {
        // Specify which class to query
        val query: ParseQuery<Post> = ParseQuery.getQuery(Post::class.java)
        query.include(Post.KEY_USER)
        query.addDescendingOrder("createdAt")
        query.findInBackground(object: FindCallback<Post> {
            override fun done(posts: MutableList<Post>?, e: ParseException?) {
                if(e != null){
                    Log.e("FeedFragment", "Error fetching posts")
                }
                else {
                    if(posts != null) {
                        for(post in posts) {
                            Log.i("FeedFragment", "Post: " + post.getDescription())
                        }

                        allPosts.addAll(posts)
                        adapter.notifyDataSetChanged()
                    }
                }
            }

        })
    }
}
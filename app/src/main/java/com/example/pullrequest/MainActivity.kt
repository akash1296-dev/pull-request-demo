package com.example.pullrequest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pullrequest.adapter.ClosedRequestAdapter
import com.example.pullrequest.dtos.ClosedPullRequest
import com.example.pullrequest.viewmodel.GitHubRepoViewModel

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProvider(this).get(GitHubRepoViewModel::class.java)

        viewModel.fetchClosedPullRequest("closed")?.observe(this) { listClosedRequest ->
            showData(listClosedRequest)
        }

    }

    private fun showData(listClosedRequest: List<ClosedPullRequest>?) {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView?.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = ClosedRequestAdapter(listClosedRequest!!, Glide.with(this@MainActivity))
        }
    }
}
package com.example.pullrequest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pullrequest.adapter.ClosedRequestAdapter
import com.example.pullrequest.dtos.ClosedPullRequest
import com.example.pullrequest.viewmodel.GitHubRepoViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val progressBar = findViewById<ProgressBar>(R.id.progress_circular)
        toggleViewVisibility(progressBar, true)
        val viewModel = ViewModelProvider(this).get(GitHubRepoViewModel::class.java)

        viewModel.fetchClosedPullRequest().observe(this) { listClosedRequest ->
            if (listClosedRequest.isNullOrEmpty()) {
                toggleViewVisibility(progressBar, false)
                Toast.makeText(this, "No Closed Pull Request Available", Toast.LENGTH_SHORT)
            } else {
                toggleViewVisibility(progressBar, false)
                showData(listClosedRequest)
            }
        }

    }

    private fun showData(listClosedRequest: List<ClosedPullRequest>?) {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView?.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = ClosedRequestAdapter(listClosedRequest!!, Glide.with(this@MainActivity))
        }
    }

    private fun toggleViewVisibility(view: View, isVisible: Boolean) {
        if (isVisible) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.GONE
        }
    }
}
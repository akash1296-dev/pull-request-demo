package com.example.pullrequest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
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

    private lateinit var mRepoViewModel: GitHubRepoViewModel
    private var mProgressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mProgressBar = findViewById<ProgressBar>(R.id.progress_circular)
        mRepoViewModel = ViewModelProvider(this).get(GitHubRepoViewModel::class.java)
        loadData()
    }

    private fun loadData() {
        toggleViewVisibility(mProgressBar, true)
        if (NetworkUtil.isNetworkAvailable(this)){
            mRepoViewModel.fetchClosedPullRequest().observe(this) { listClosedRequest ->
                if (listClosedRequest.isNullOrEmpty()) {
                    toggleViewVisibility(mProgressBar, false)
                    Toast.makeText(this, "No Closed Pull Request Available", Toast.LENGTH_SHORT).show()
                } else {
                    toggleViewVisibility(mProgressBar, false)
                    showData(listClosedRequest)
                }
            }
        } else {
            toggleViewVisibility(mProgressBar, false)
            Toast.makeText(this, "Internet Connection not available", Toast.LENGTH_SHORT).show()

        }
    }

    private fun showData(listClosedRequest: List<ClosedPullRequest>?) {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView?.apply {
            recyclerView.removeAllViews()
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = ClosedRequestAdapter(listClosedRequest!!, Glide.with(this@MainActivity))
        }
    }

    private fun toggleViewVisibility(view: View?, isVisible: Boolean) {
        if (view == null) return
        if (isVisible) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.GONE
        }
    }
}
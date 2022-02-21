package com.example.pullrequest.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pullrequest.RetrofitFactory
import com.example.pullrequest.dtos.ClosedPullRequest
import com.example.pullrequest.endpoint.GitHubService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GitHubRepoViewModel() : ViewModel() {

    private val gitHubEndPoint = RetrofitFactory
        .getRetrofit()
        .create(GitHubService::class.java)
    private val TAG = "GitHubRepoViewModel"

    fun fetchClosedPullRequest(pullRequestState: String) : MutableLiveData<List<ClosedPullRequest>>? {

        val listClosedPullRequest = MutableLiveData<List<ClosedPullRequest>>()

        gitHubEndPoint.fetchClosedPullRequest("closed").enqueue(object : Callback<List<ClosedPullRequest>> {
            override fun onResponse(call: Call<List<ClosedPullRequest>>, response: Response<List<ClosedPullRequest>>) {
                Log.d(TAG, "data is ${response.body()}")
                if (response.isSuccessful) {
                    listClosedPullRequest.postValue(response.body())
                } else {
                    return listClosedPullRequest.postValue(null)
                }
            }

            override fun onFailure(call: Call<List<ClosedPullRequest>>, t: Throwable) {
                Log.e(TAG, t.localizedMessage)
            }
        })
        return listClosedPullRequest
    }
}
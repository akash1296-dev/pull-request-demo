package com.example.pullrequest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.myapplication.R
import com.example.pullrequest.DateUtil
import com.example.pullrequest.dtos.ClosedPullRequest

class ClosedRequestAdapter(
    private val listClosedRequest: List<ClosedPullRequest>,
    private val requestManager: RequestManager
) : RecyclerView.Adapter<ClosedRequestAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_closed_request_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        listClosedRequest[position].apply {
            requestManager
                .asBitmap()
                .load(gitUser?.userAvatarUrl)
                .into(holder.userAvatar)

            holder.userName.text = gitUser?.userName
            holder.title.text = pullRequestTitle


            holder.createdAt.text = String.format("Created At: %s", DateUtil.getFormattedDate(pullRequestCreateDate))
            holder.closedAt.text = String.format("Closed At: %s", DateUtil.getFormattedDate(pullRequestClosedDate))
        }
    }

    override fun getItemCount(): Int {
        return listClosedRequest.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userAvatar: ImageView = itemView.findViewById(R.id.iv_userAvatar)
        val userName: TextView = itemView.findViewById(R.id.tv_username)
        val title: TextView = itemView.findViewById(R.id.tv_title)
        val createdAt: TextView = itemView.findViewById(R.id.tv_createdAt)
        val closedAt: TextView = itemView.findViewById(R.id.tv_closedAt)
    }
}
package com.example.gadsleaderboard.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gadsleaderboard.R
import com.example.gadsleaderboard.data.models.LearningLeader
import kotlinx.android.synthetic.main.list_item_learner.view.*

class LearningLeaderRecyclerAdapter :
    RecyclerView.Adapter<LearningLeaderRecyclerAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_learner, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val leader = differ.currentList[position]
        holder.itemView.apply {
            tv_name.text = leader.name
            tv_country.text = leader.country
            tv_info.text = "${leader.hours} learning hours"
            Glide.with(this).load(leader.badgeUrl).into(iv_image)
        }
    }

    override fun getItemCount() = differ.currentList.size

    private val callback = object : DiffUtil.ItemCallback<LearningLeader>() {
        override fun areItemsTheSame(oldItem: LearningLeader, newItem: LearningLeader): Boolean {
            return "${oldItem.name}-${oldItem.hours}-${oldItem.country}" == "${newItem.name}-${newItem.hours}-${newItem.country}"
        }

        override fun areContentsTheSame(oldItem: LearningLeader, newItem: LearningLeader): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callback)
}
package com.project.githubuserproject.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.githubuserproject.R
import com.project.githubuserproject.databinding.ProfileRowBinding
import com.project.githubuserproject.model.data.User
import kotlinx.android.synthetic.main.profile_row.view.*

class MyAdapter : RecyclerView.Adapter<MyAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallBack(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    private val mData = ArrayList<User>()
    fun setData(items: ArrayList<User>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val mView =
                LayoutInflater.from(parent.context).inflate(R.layout.profile_row, parent, false)
        return ListViewHolder(mView)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(mData[position])
        holder.itemView.setOnClickListener{onItemClickCallback.onItemClicked(mData[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int = mData.size

        inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val binding = ProfileRowBinding.bind(itemView)
            fun bind(user: User) {
                with(itemView) {
                    text_name.text = user.detail
                    profile_name.text = itemView.context.getString(R.string.yulski, user.name)
                    Glide.with(itemView.context).load(user.photo).into(binding.imgItemPhoto)
                }
            }
        }
    interface OnItemClickCallback {
        fun onItemClicked(data: User)
    }
    }

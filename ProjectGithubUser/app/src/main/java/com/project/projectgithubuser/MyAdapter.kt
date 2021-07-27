package com.project.projectgithubuser

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class MyAdapter(private val listProfile: ArrayList<Profile>) : RecyclerView.Adapter<MyAdapter.ListViewHolder>(){

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallBack(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.proflie_nama)
        var tvDetail: TextView = itemView.findViewById(R.id.profile_detail)
        var tvPekerjaan: TextView = itemView.findViewById(R.id.profile_work)
        var gmbrFoto: ImageView = itemView.findViewById(R.id.img_item_photo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.profile_row, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listProfile.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val profile = listProfile[position]

        Glide.with(holder.itemView.context)
            .load(profile.photo)
            .apply(RequestOptions(). override(55,55))
            .into(holder.gmbrFoto)

        holder.tvName.text = profile.name
        holder.tvDetail.text = profile.detail
        holder.tvPekerjaan.text = profile.pekerjaan
        holder.itemView.setOnClickListener{onItemClickCallback.onItemClicked(listProfile[holder.adapterPosition]) }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Profile)
    }
}
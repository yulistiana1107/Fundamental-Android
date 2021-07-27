package com.project.consumerapp.view.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.consumerapp.databinding.ActivityMainBinding
import com.project.consumerapp.model.data.User
import com.project.consumerapp.viewmodel.FavoriteViewModel
import com.project.consumerapp.viewmodel.MyAdapter

class MainActivity : AppCompatActivity() {

    companion object {
        const val YULIS_USER = "yulis_user"
    }

    private lateinit var yulisbinding: ActivityMainBinding
    private lateinit var yulisadapter: MyAdapter
    private lateinit var yulisviewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        yulisbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(yulisbinding.root)

        yulisadapter= MyAdapter()
        yulisadapter.notifyDataSetChanged()

        yulisviewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)


        yulisbinding.apply {
            yulistianacode.setHasFixedSize(true)
            yulistianacode.layoutManager = LinearLayoutManager(this@MainActivity)
            yulistianacode.adapter = yulisadapter
        }

        yulisviewModel.yulisFavoriteUser(this)

        yulisviewModel.yulisFavorite()?. observe(this, Observer{
            if (it != null){
                yulisadapter.setData(it)
            }

        })

        onClicked()

    }

    fun onClicked(){
        yulisadapter.setOnItemClickCallBack(object : MyAdapter.OnItemClickCallback {
            override fun onItemClicked(yulis: User) {
                val moveProfile = Intent(this@MainActivity, FullProfileActivity::class.java)
                moveProfile.putExtra(YULIS_USER, yulis)
                moveProfile.putExtra(FullProfileActivity.YULIS_USERNAME, yulis.name)
                moveProfile.putExtra(FullProfileActivity.YULIS_ID, yulis.id)
                moveProfile.putExtra(FullProfileActivity.YULIS_URL, yulis.photo)
                moveProfile.putExtra(FullProfileActivity.YULIS_NAME, yulis.detail)
                moveProfile.putExtra(FullProfileActivity.YULIS_FOLLOWERS, yulis.followers)
                moveProfile.putExtra(FullProfileActivity.YULIS_FOLLOWING, yulis.following)
                moveProfile.putExtra(FullProfileActivity.YULIS_WORKPLACE, yulis.workplace)
                moveProfile.putExtra(FullProfileActivity.YULIS_LOCATION, yulis.location)
                moveProfile.putExtra(FullProfileActivity.YULIS_REPOSITORIES, yulis.repositories)
                startActivity(moveProfile)
            }
        })
    }
}
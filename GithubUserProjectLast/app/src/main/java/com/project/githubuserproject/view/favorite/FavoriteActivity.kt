package com.project.githubuserproject.view.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.githubuserproject.databinding.ActivityFavoriteBinding
import com.project.githubuserproject.model.data.User
import com.project.githubuserproject.model.local.Favorite
import com.project.githubuserproject.view.main.FullProfileActivity
import com.project.githubuserproject.view.main.MainActivity
import com.project.githubuserproject.viewmodel.FavoriteViewModel
import com.project.githubuserproject.viewmodel.MyAdapter

class FavoriteActivity : AppCompatActivity() {

    private lateinit var yulisbinding: ActivityFavoriteBinding
    private lateinit var yulisadapter: MyAdapter
    private lateinit var yulisviewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        yulisbinding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(yulisbinding.root)

        yulisadapter= MyAdapter()
        yulisadapter.notifyDataSetChanged()

        yulisviewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)

        yulisadapter.setOnItemClickCallBack(object : MyAdapter.OnItemClickCallback {
            override fun onItemClicked(yulis: User) {
                val moveProfile = Intent(this@FavoriteActivity, FullProfileActivity::class.java)
                moveProfile.putExtra(MainActivity.YULIS_USER, yulis)
                moveProfile.putExtra(FullProfileActivity.YULIS_USERNAME, yulis.name)
                moveProfile.putExtra(FullProfileActivity.YULIS_ID, yulis.id)
                moveProfile.putExtra(FullProfileActivity.YULIS_URL, yulis.photo)
                moveProfile.putExtra(FullProfileActivity.YULIS_NAME, yulis.detail)
                moveProfile.putExtra(FullProfileActivity.YULIS_NAME, yulis.detail)
                moveProfile.putExtra(FullProfileActivity.YULIS_FOLLOWERS, yulis.followers)
                moveProfile.putExtra(FullProfileActivity.YULIS_FOLLOWING, yulis.following)
                moveProfile.putExtra(FullProfileActivity.YULIS_WORKPLACE, yulis.workplace)
                moveProfile.putExtra(FullProfileActivity.YULIS_LOCATION, yulis.location)
                moveProfile.putExtra(FullProfileActivity.YULIS_REPOSITORIES, yulis.repositories)
                startActivity(moveProfile)
            }
        })

        yulisbinding.apply {
            yulistianacode.setHasFixedSize(true)
            yulistianacode.layoutManager = LinearLayoutManager(this@FavoriteActivity)
            yulistianacode.adapter = yulisadapter
        }

        yulisviewModel.getFavorite()?. observe(this, Observer{
            if (it != null){
                val list = yulisMapListOf(it)
                yulisadapter.setData(list)
            }

        })

    }

    private fun yulisMapListOf(favorite: List<Favorite>?): ArrayList<User> {
        val yulisListFavorite = ArrayList<User>()
        if (favorite != null) {
            for (yulis in favorite){
                val mapped = User(
                        yulis.name,
                        yulis.detail,
                        yulis.photo,
                        yulis.id,
                        yulis.followers,
                        yulis.following,
                        yulis.workplace,
                        yulis.location,
                        yulis.repositories
                )
                yulisListFavorite.add(mapped)
            }
        }
        return yulisListFavorite
    }
}
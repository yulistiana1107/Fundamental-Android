package com.project.githubuserproject.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.content.Intent
import android.view.*
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.githubuserproject.viewmodel.MainViewModel
import com.project.githubuserproject.viewmodel.MyAdapter
import com.project.githubuserproject.R
import com.project.githubuserproject.databinding.ActivityMainBinding
import com.project.githubuserproject.model.data.User
import com.project.githubuserproject.view.favorite.FavoriteActivity
import com.project.githubuserproject.view.settings.SettingsActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(){
    companion object {
        const val YULIS_USER = "yulis_user"
    }

    private var listGithub: ArrayList<User> = ArrayList()
    private lateinit var yulisadapter: MyAdapter
    private lateinit var yulisbinding: ActivityMainBinding
    private lateinit var yulismainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        yulisbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(yulisbinding.root)

        yulisadapter= MyAdapter()
        yulismainViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MainViewModel::class.java)

        searchData()
        onClicked()
        configMainViewModel(yulisadapter)

        yulismainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)

        yulisbinding.profileUser.layoutManager = LinearLayoutManager(this)
        yulisbinding.profileUser.adapter = yulisadapter

        yulismainViewModel.getDataGit(applicationContext)
        showLoading(true)

        yulisadapter.notifyDataSetChanged()
        profileUser.adapter = yulisadapter

    }

    private fun configMainViewModel(adapter: MyAdapter) {
        yulismainViewModel.getListUsers().observe(this, androidx.lifecycle.Observer {
            if (it != null) {
                adapter.setData(it)
                showLoading(false)
            }
        })
    }

    private fun searchData(){
        val search = findViewById<android.widget.SearchView>(R.id.search)
        search.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (query.isNotEmpty()) {
                    listGithub.clear()
                    yulismainViewModel.getDataGitSearch(query, applicationContext)
                    showLoading(true)
                } else {
                    return true
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
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

    private fun showLoading(state: Boolean) {
        if (state) {
            yulisbinding.progressBar.visibility = View.VISIBLE
        } else {
            yulisbinding.progressBar.visibility = View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(selectedMode: Int) {
        when (selectedMode) {

            R.id.menu -> {
                val bioData = Intent(this@MainActivity, LoginActivity::class.java)
                startActivity(bioData)
            }

            R.id.favorite_menu ->{
                val favorite = Intent(this@MainActivity, FavoriteActivity::class.java)
                startActivity(favorite)
            }

            R.id.settings_menu ->{
                val settings = Intent(this@MainActivity, SettingsActivity::class.java)
                startActivity(settings)
            }
        }
    }
}
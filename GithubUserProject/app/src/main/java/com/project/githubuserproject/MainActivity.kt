package com.project.githubuserproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.content.Intent
import android.view.*
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.githubuserproject.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(){
    companion object {
        const val EXTRA_USER = "extra_user"
    }

    private var listGithub: ArrayList<User> = ArrayList()
    private lateinit var adapter: MyAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter= MyAdapter()
        mainViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MainViewModel::class.java)

        searchData()
        viewConfig()
        runGetDataGit()
        onClicked()
        configMainViewModel(adapter)

        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)
    }

    private fun viewConfig() {
        binding.profileUser.layoutManager = LinearLayoutManager(this)
        binding.profileUser.adapter = adapter

        adapter.notifyDataSetChanged()
        profileUser.adapter = adapter
    }


    private fun configMainViewModel(adapter: MyAdapter) {
        mainViewModel.getListUsers().observe(this, androidx.lifecycle.Observer {
            if (it != null) {
                adapter.setData(it)
                showLoading(false)
            }
        })
    }

    private fun runGetDataGit() {
        mainViewModel.getDataGit(applicationContext)
        showLoading(true)
    }

    private fun searchData(){
        val search = findViewById<android.widget.SearchView>(R.id.search)
        search.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (query.isNotEmpty()) {
                    listGithub.clear()
                    viewConfig()
                    mainViewModel.getDataGitSearch(query, applicationContext)
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
        adapter.setOnItemClickCallBack(object : MyAdapter.OnItemClickCallback {
            override fun onItemClicked(user: User) {
                val moveProfile = Intent(this@MainActivity, FullProfileActivity::class.java)
                moveProfile.putExtra(EXTRA_USER, user)
                startActivity(moveProfile)
            }
        })
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
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
        }
    }
}
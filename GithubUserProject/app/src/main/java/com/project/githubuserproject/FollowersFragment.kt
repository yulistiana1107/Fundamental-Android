package com.project.githubuserproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_followers.*
import kotlinx.android.synthetic.main.fragment_following.*


class FollowersFragment(private val username: String) : Fragment() {
    companion object {
        const val EXTRA_DETAIL2 = "extra_detail"
    }

    private lateinit var adapter: MyAdapter
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_followers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MyAdapter()
        mainViewModel = ViewModelProvider(
                this, ViewModelProvider.NewInstanceFactory()
        ).get(MainViewModel::class.java)

        val dataUser = activity!!.intent.getParcelableExtra<User>(EXTRA_DETAIL2)
        config()

        mainViewModel.getDataFollowers(activity!!.applicationContext, username)
        showLoading(true)

        mainViewModel.getListUsers().observe(activity!!, Observer {
            if (it != null) {
                adapter.setData(it)
                showLoading(false)
            }
        })
    }

    private fun config() {
        recyclerviewfollowers.layoutManager = LinearLayoutManager(activity)
        recyclerviewfollowers.setHasFixedSize(true)
        recyclerviewfollowers.adapter = adapter
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            progressBar1.visibility = View.VISIBLE
        } else {
            progressBar1.visibility = View.INVISIBLE
        }
    }
}
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

class FollowingFragment(private val username: String) : Fragment() {
    companion object {
        const val EXTRA_DETAIL = "extra_detail"
    }

    private lateinit var adapter: MyAdapter
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_following, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MyAdapter()
        mainViewModel = ViewModelProvider(
                this, ViewModelProvider.NewInstanceFactory()
        ).get(MainViewModel::class.java)

        val dataUser = activity!!.intent.getParcelableExtra<User>(EXTRA_DETAIL)
        config()

        mainViewModel.getDataFollowing(activity!!.applicationContext, username)
        showLoading(true)

        mainViewModel.getListUsers().observe(activity!!, Observer {
            if (it != null) {
                adapter.setData(it)
                showLoading(false)
            }
        })
    }

    private fun config() {
        recyclerviewfollowing.layoutManager = LinearLayoutManager(activity)
        recyclerviewfollowing.setHasFixedSize(true)
        recyclerviewfollowing.adapter = adapter
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            progressBar2.visibility = View.VISIBLE
        } else {
            progressBar2.visibility = View.INVISIBLE
        }
    }
}
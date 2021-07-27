package com.project.consumerapp.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.consumerapp.R
import com.project.consumerapp.model.data.User
import com.project.consumerapp.view.main.FullProfileActivity
import com.project.consumerapp.view.main.MainActivity
import com.project.consumerapp.viewmodel.MainViewModel
import com.project.consumerapp.viewmodel.MyAdapter
import kotlinx.android.synthetic.main.fragment_followers.*


class FollowersFragment(private val username: String) : Fragment() {
    companion object {
        const val YULIS_DETAIL2 = "extra_detail"
    }

    private lateinit var yulisadapter: MyAdapter
    private lateinit var yulismainViewModel: MainViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_followers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        yulisadapter = MyAdapter()
        yulismainViewModel = ViewModelProvider(
                this, ViewModelProvider.NewInstanceFactory()
        ).get(MainViewModel::class.java)

        activity!!.intent.getParcelableExtra<User>(YULIS_DETAIL2)
        config()
        onClicked()

        yulismainViewModel.getDataFollowers(activity!!.applicationContext, username)
        showLoading(true)

        yulismainViewModel.getListUsers().observe(activity!!, Observer {followerItems ->
            if (followerItems != null) {
                yulisadapter.setData(followerItems)
                showLoading(false)
            }
        })
    }


    fun onClicked(){
        yulisadapter.setOnItemClickCallBack(object : MyAdapter.OnItemClickCallback {
            override fun onItemClicked(yulis: User) {
                activity?.let{
                    val intent = Intent (it, FullProfileActivity::class.java)
                    intent.putExtra(MainActivity.YULIS_USER, yulis)
                    intent.putExtra(FullProfileActivity.YULIS_USERNAME, yulis.name)
                    intent.putExtra(FullProfileActivity.YULIS_ID, yulis.id)
                    intent.putExtra(FullProfileActivity.YULIS_URL, yulis.photo)
                    intent.putExtra(FullProfileActivity.YULIS_NAME, yulis.detail)
                    it.startActivity(intent)
                }
            }
        })
    }

    private fun config() {
        yulistianacodefollowers.layoutManager = LinearLayoutManager(activity)
        yulistianacodefollowers.setHasFixedSize(true)
        yulistianacodefollowers.adapter = yulisadapter
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            yulisProgressBarFollowers.visibility = View.VISIBLE
        } else {
            yulisProgressBarFollowers.visibility = View.INVISIBLE
        }
    }
}
package com.project.consumerapp.view.main

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.project.consumerapp.R
import com.project.consumerapp.model.data.User
import com.project.consumerapp.viewmodel.FavoriteViewModel
import com.project.consumerapp.viewmodel.SectionPagerAdapter
import kotlinx.android.synthetic.main.activity_full_profile.*

class FullProfileActivity : AppCompatActivity() {

    private lateinit var yulisviewModel: FavoriteViewModel

    companion object {
        @StringRes
        val YULIS_TAB_TITLES = intArrayOf(
                R.string.followers,
                R.string.following
        )

        const val YULIS_ID = "yulis_id"
        const val YULIS_USERNAME = "yulis_username"
        const val YULIS_URL = "yulis_url"
        const val YULIS_NAME= "yulis_name"
        const val YULIS_FOLLOWERS ="yulis_followers"
        const val YULIS_FOLLOWING ="yulis_following"
        const val YULIS_WORKPLACE= "yulis_workplace"
        const val YULIS_LOCATION= "yulis_location"
        const val YULIS_REPOSITORIES= "yulis_repositories"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_profile)


        val orientation = resources.configuration.orientation
                if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    viewpager.layoutParams.height = resources.getDimension(R.dimen.height).toInt()
                } else {
                    viewpager.layoutParams.height = resources.getDimension(R.dimen.height1).toInt()
                }

        if (supportActionBar != null) {
            supportActionBar?.title = "Detail"
        }
        intent.getIntExtra(YULIS_ID, 0)
        intent.getStringExtra(YULIS_URL)
        intent.getStringExtra(YULIS_USERNAME)
        intent.getStringExtra(YULIS_NAME)
        intent.getStringExtra(YULIS_FOLLOWERS)
        intent.getStringExtra(YULIS_FOLLOWING)
        intent.getStringExtra(YULIS_WORKPLACE)
        intent.getStringExtra(YULIS_LOCATION)
        intent.getStringExtra(YULIS_REPOSITORIES)

        yulisviewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)
        setData()
    }

    private fun setData() {
        val dataUser = intent.getParcelableExtra<User>(MainActivity.YULIS_USER)
        Glide.with(this)
            .load(dataUser?.photo)
            .apply(RequestOptions().override(150, 130))
            .into(img_item_photo_profil)
        full_name.text = dataUser?.detail
        full_detail.text = getString(R.string.yulski, dataUser?.name)
        work_place.text = dataUser?.workplace
        location_address.text = dataUser?.location
        text_following.text = dataUser?.following
        text_followers.text = dataUser?.followers
        textView.text = dataUser?.repositories


        val sectionPagerAdapter = SectionPagerAdapter(dataUser?.name as String, this, supportFragmentManager)
        viewpager.adapter = sectionPagerAdapter
        tabs.setupWithViewPager(viewpager)
        supportActionBar?.elevation = 0f
    }


}
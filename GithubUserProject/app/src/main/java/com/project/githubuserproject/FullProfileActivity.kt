package com.project.githubuserproject

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_full_profile.*

class FullProfileActivity : AppCompatActivity() {

    companion object {
        @StringRes
        val TAB_TITLES = intArrayOf(
            R.string.followers,
            R.string.following
        )
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

        setData()

    }

    private fun setData() {
        val dataUser = intent.getParcelableExtra<User>(MainActivity.EXTRA_USER)
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
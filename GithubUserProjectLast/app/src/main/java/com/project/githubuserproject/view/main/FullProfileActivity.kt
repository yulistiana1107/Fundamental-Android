package com.project.githubuserproject.view.main

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ToggleButton
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.project.githubuserproject.R
import com.project.githubuserproject.viewmodel.SectionPagerAdapter
import com.project.githubuserproject.model.data.User
import com.project.githubuserproject.viewmodel.DetailUserViewModel
import kotlinx.android.synthetic.main.activity_full_profile.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FullProfileActivity : AppCompatActivity() {

    private lateinit var yulisviewModel: DetailUserViewModel
    private lateinit var yulisbtnToggleButton: ToggleButton

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

        yulisbtnToggleButton = findViewById(R.id.yulistogglefavorite)

        val orientation = resources.configuration.orientation
                if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    viewpager.layoutParams.height = resources.getDimension(R.dimen.height).toInt()
                } else {
                    viewpager.layoutParams.height = resources.getDimension(R.dimen.height1).toInt()
                }

        if (supportActionBar != null) {
            supportActionBar?.title = "Detail"
        }
        val id = intent.getIntExtra(YULIS_ID, 0)
        val avatarUrl = intent.getStringExtra(YULIS_URL)
        val username = intent.getStringExtra(YULIS_USERNAME)
        val fullName = intent.getStringExtra(YULIS_NAME)
        val followers = intent.getStringExtra(YULIS_FOLLOWERS)
        val following = intent.getStringExtra(YULIS_FOLLOWING)
        val workplace = intent.getStringExtra(YULIS_WORKPLACE)
        val location = intent.getStringExtra(YULIS_LOCATION)
        val repositories = intent.getStringExtra(YULIS_REPOSITORIES)

        yulisviewModel = ViewModelProvider(this).get(DetailUserViewModel::class.java)

        var yulisChecked = false
        CoroutineScope(Dispatchers.IO).launch {
            val count = yulisviewModel.check(id)
            withContext(Dispatchers.Main){
                if (count != null){
                    if (count>0){
                        yulisbtnToggleButton.isChecked =  true
                        yulisChecked = true
                    }else{
                        yulisbtnToggleButton.isChecked =  false
                        yulisChecked = false
                    }
                }
            }
        }

        yulisbtnToggleButton.setOnClickListener{
            yulisChecked = !yulisChecked
            if (yulisChecked){
                username?.let { it1 -> avatarUrl?.let { it2 -> fullName?.let { it3 -> followers?.let { it4 -> following?.let { it5 -> workplace?.let { it6 -> location?.let { it7 -> repositories?.let { it8 -> yulisviewModel.addFavorite(it1, id, it2, it3, it4, it5, it6, it7, it8) } } } } } } } }
            } else{
                yulisviewModel.removeFavorite(id)
            }
            yulisbtnToggleButton.isChecked = yulisChecked
        }
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
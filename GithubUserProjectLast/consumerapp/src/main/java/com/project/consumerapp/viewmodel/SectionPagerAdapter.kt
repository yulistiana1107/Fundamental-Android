package com.project.consumerapp.viewmodel

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.project.consumerapp.view.fragment.FollowersFragment
import com.project.consumerapp.view.fragment.FollowingFragment
import com.project.consumerapp.view.main.FullProfileActivity

class SectionPagerAdapter(private val username: String, private val mContext: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val pages = listOf(
            FollowersFragment(username),
            FollowingFragment(username)
    )
    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mContext.resources.getString(FullProfileActivity.YULIS_TAB_TITLES[position])
    }
}
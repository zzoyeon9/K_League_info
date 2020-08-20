package com.example.k_league_info

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.k_league_info.ScoredetailFragment.*

class ScoredetailFPA(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    private var fragments = ArrayList<Fragment>()

    override fun getItem(position: Int): Fragment {
        return fragments.get(position)
    }

    override fun getCount(): Int = fragments.size


    fun addItems(fragment: Fragment) {
        fragments.add(fragment)
    }
}
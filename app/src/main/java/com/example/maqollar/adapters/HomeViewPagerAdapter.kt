package com.example.maqollar.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class HomeViewPagerAdapter(var titleList:List<String>,var fragmentList: List<Fragment>,fragmentManager: FragmentManager) :FragmentPagerAdapter(fragmentManager){

    override fun getCount(): Int = titleList.size

    override fun getItem(position: Int): Fragment {
       return fragmentList[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titleList[position]
    }

}
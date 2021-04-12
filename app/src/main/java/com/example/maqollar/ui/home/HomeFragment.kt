package com.example.maqollar.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.fragment.app.Fragment
import com.example.maqollar.MainActivity
import com.example.maqollar.R
import com.example.maqollar.adapters.HomeViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {


    lateinit var root: View
    lateinit var fragmentList: ArrayList<Fragment>
    lateinit var tabTitles:ArrayList<String>
    lateinit var homeViewPagerAdapter: HomeViewPagerAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        root = inflater.inflate(R.layout.fragment_home, container, false)
        val mainActivity = (activity) as MainActivity
//        mainActivity.supportActionBar?.hide()
        fragmentList = ArrayList()
        fragmentList.add(MaqolFragment())
        fragmentList.add(RuknFragment())

        loadTitles()

        homeViewPagerAdapter = HomeViewPagerAdapter(tabTitles,fragmentList,childFragmentManager)
        root.home_viewpager.adapter = homeViewPagerAdapter
        root.home_tablayout.setupWithViewPager(root.home_viewpager)

        setupTabItems()
        root.home_tablayout.addOnTabSelectedListener(object:TabLayout.OnTabSelectedListener{
            @SuppressLint("ResourceAsColor")
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab?.position==0) {
                    val customView = tab.customView
                    val tabImage = customView?.findViewById<ImageView>(R.id.tab_image1)
                    val tabText = customView?.findViewById<TextView>(R.id.tab_tv1)
                    tabText?.setTextColor(R.color.black)
                    tabImage?.setImageResource((R.drawable.ic_tablayout_item1))
                }else{
                    val customView = tab?.customView
                    val tabImage = customView?.findViewById<ImageView>(R.id.tab_image1)
                    val tabText = customView?.findViewById<TextView>(R.id.tab_tv1)
                    tabText?.setTextColor(R.color.black)
                    tabImage?.setImageResource((R.drawable.ic_tablayout_item22))
                }
            }

            @SuppressLint("ResourceAsColor")
            override fun onTabUnselected(tab: TabLayout.Tab?) {
                if (tab?.position==0) {
                    val customView = tab.customView
                    val image = customView?.findViewById<ImageView>(R.id.tab_image1)
                    val text1 = customView?.findViewById<TextView>(R.id.tab_tv1)
                    text1?.setTextColor(R.color.bottom_unselect_color)
                    image?.setImageResource((R.drawable.ic_tablayout_item11))
                }else{
                    val customView = tab?.customView
                    val image = customView?.findViewById<ImageView>(R.id.tab_image1)
                    image?.setImageResource((R.drawable.ic_tablayout_item2))
                    val text1 = customView?.findViewById<TextView>(R.id.tab_tv1)
                    text1?.setTextColor(R.color.bottom_unselect_color)
                }

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        root.icon1.setOnClickListener {
            mainActivity.drawer_layout.open()
        }



        return root
    }

    @SuppressLint("ResourceAsColor")
    private fun setupTabItems() {
        for (i in tabTitles.indices){
            val tab = root.home_tablayout.getTabAt(i)
            val view = LayoutInflater.from(root.context).inflate(R.layout.tab_item1, null, false)
            val tabImage = view.findViewById<ImageView>(R.id.tab_image1)
            val tabText = view.findViewById<TextView>(R.id.tab_tv1)
            tabText.text = tabTitles[i]
            if (i==0){
//                tabImage.setImageDrawable(getDrawable(root.context,R.drawable.ic_tablayout_item1))
            }else{
                tabImage.setImageDrawable(getDrawable(root.context,R.drawable.ic_tablayout_item2))
                tabText.setTextColor(R.color.bottom_unselect_color)
            }
            tab?.customView = view
        }
    }

    private fun loadTitles() {
        tabTitles = ArrayList()
        tabTitles.add("Barcha maqollar")
        tabTitles.add("Barcha ruknlar")
    }
}
package com.example.lt1.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter

/*class PagerAdapter(
    private val resultBundle: Bundle,
    private val fragments: ArrayList<Fragment>,
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        fragments[position].arguments = resultBundle
        return fragments[position]
    }
}*/
class PagerAdapter(
    private val resultBundle: Bundle,
    private val fragments: ArrayList<Fragment>,
    private val title: ArrayList<String>,
    fm: FragmentManager
): FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getItem(position: Int): Fragment {
        fragments[position].arguments = resultBundle
        return fragments[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return title[position]
    }
}
/*viewpager is a layout widget that allows you to scroll left or right to navigate between pages,each page is represented by a fragment.
Pager adaper is used to provide these pages to the viewpger
 The Bundle is a container for data that is passed between activities or fragments, and it is used to pass data to the fragments that will be displayed in the ViewPager.
  The ArrayList of Fragments is a list of the fragments that will be displayed in the ViewPager,(created in details activity)
   and the ArrayList of Strings is a list of titles that will be displayed for each fragment.
    The FragmentManager is a system service that is responsible for managing the fragments in the app.
 */
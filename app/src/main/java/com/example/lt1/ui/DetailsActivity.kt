package com.example.lt1.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.navArgs
import androidx.viewpager.widget.ViewPager
import com.example.lt1.R
import com.example.lt1.adapters.PagerAdapter
import com.example.lt1.ui.fragments.ingredients.IngredientsFragment
import com.example.lt1.ui.fragments.instructions.InstructionsFragment
import com.example.lt1.ui.fragments.overview.OverviewFragment
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.tabs.TabLayout

class DetailsActivity : AppCompatActivity() {

    private val args by navArgs<DetailsActivityArgs>()
    /*  a Kotlin class that is using the navArgs delegate to retrieve arguments that have been passed to an activity. The navArgs delegate is a tool provided by
     the Android Navigation component that allows you to easily retrieve and use arguments that have been passed to an activity or fragment using the Android Navigation component.*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        var tabLayout = findViewById<(TabLayout)>(R.id.tabLayout)
        var viewPager = findViewById<ViewPager>(R.id.viewPager)
        setSupportActionBar(toolbar)
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val fragments = ArrayList<Fragment>()
        fragments.add(OverviewFragment())
        fragments.add(IngredientsFragment())
        fragments.add(InstructionsFragment())

        val titles = ArrayList<String>()
        titles.add("Overview")
        titles.add("Ingredients")
        titles.add("Instructions")

        val resultBundle = Bundle()
        resultBundle.putParcelable("recipeBundle", args.result)

        val adapter = PagerAdapter(
            resultBundle,
            fragments,
            titles,
            supportFragmentManager
        )

        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

}
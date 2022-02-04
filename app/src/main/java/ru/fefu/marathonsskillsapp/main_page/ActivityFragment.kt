package ru.fefu.marathonsskillsapp.main_page

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import ru.fefu.marathonsskillsapp.R
import ru.fefu.marathonsskillsapp.main_page.adapters.ViewPagerAdapter

class ActivityFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val myFragment = inflater.inflate(R.layout.activity_fragment, container, false)
        val tabLayout = myFragment.findViewById<TabLayout>(R.id.tab_layout)
        val viewPager2 = myFragment.findViewById<ViewPager2>(R.id.pager)
        val adapter = ViewPagerAdapter(childFragmentManager, lifecycle)
        viewPager2.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager2) {tab, position->
            when(position) {
                0->{
                    tab.text = "Предстоящие марафоны"
                }
//                1->{
//                    tab.text = "Прошедшие"
//                }
            }
        }.attach()



        return myFragment
    }

}

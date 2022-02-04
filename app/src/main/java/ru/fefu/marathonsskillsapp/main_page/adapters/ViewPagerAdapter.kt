package ru.fefu.marathonsskillsapp.main_page.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.fefu.marathonsskillsapp.main_page.MarathonFragment


class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int = 1
    override fun createFragment(position: Int): Fragment {
        return MarathonFragment()
//        when(position) {
//            0->{
//                MarathonFragment()
//            }
//            1->{
//                UsersActivityFragment()
//            }
//            else->{
//                ActivityFragment()
//            }
//        }
    }

}
package ru.fefu.marathonsskillsapp.main_page

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import ru.fefu.marathonsskillsapp.R
import ru.fefu.marathonsskillsapp.databinding.ActivityMarathonFragmentBinding
import ru.fefu.marathonsskillsapp.main_page.adapters.ItemAdapter
import ru.fefu.marathonsskillsapp.main_page.lists.ListItem
import ru.fefu.marathonsskillsapp.main_page.lists.MarathonListRepository


class MarathonFragment :
    BaseFragment<ActivityMarathonFragmentBinding>(R.layout.activity_marathon_fragment) {

    private val listRepository = MarathonListRepository()
    private val adapterItems = ItemAdapter(listRepository.getItem())

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.rcView) {
            adapter = adapterItems
            layoutManager = LinearLayoutManager(requireContext())
        }

        adapterItems.setItemClickListener {
            val manager = activity?.supportFragmentManager?.findFragmentByTag("activityFragment")?.childFragmentManager
            manager?.beginTransaction()?.apply {
                manager.fragments.forEach(::hide)
                replace(
                    R.id.activity_fragment_switch_container,
                    MarathonDetailsFragment.newInstance(listRepository.getItem()[it] as ListItem.Item)
                )
                addToBackStack(null)
                commit()
            }
        }


    }
}
package ru.fefu.marathonsskillsapp.main_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.fefu.marathonsskillsapp.R
import ru.fefu.marathonsskillsapp.databinding.ActivityMarathonDetailsFragmentBinding
import ru.fefu.marathonsskillsapp.main_page.lists.ListItem


class MarathonDetailsFragment(details: ListItem.Item) :
    BaseFragment<ActivityMarathonDetailsFragmentBinding>(R.layout.activity_marathon_details_fragment) {

    private var _binding: ActivityMarathonDetailsFragmentBinding? = null
    override val binding get() = _binding!!
    private val detail = details

    companion object {
        fun newInstance(info: ListItem.Item): MarathonDetailsFragment {
            return MarathonDetailsFragment(info)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textMyDistance.text = detail.activity
        binding.textMyDate.text = detail.cost
        binding.textMyTime.text = detail.time
        binding.textMyStartTime.text = detail.startTime
        binding.textMyInfo.text = detail.info
        binding.toolbarMy.title =  detail.distance
        binding.textUserName.text = detail.user
        binding.toolbarMy.setNavigationOnClickListener {
            activity?.onBackPressed()
        }

        binding.toolbarMy.setOnMenuItemClickListener {
            true
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ActivityMarathonDetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }


}
package ru.fefu.marathonsskillsapp.main_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import marathonsskillsapp.R
import marathonsskillsapp.databinding.ActivitySwitchFragmentBinding

class ActivityFragmentSwitch: Fragment() {
    private var _binding: ActivitySwitchFragmentBinding? = null

    private val binding get() = _binding!!

//    companion object {
//        fun newInstance(): ActivityFragmentSwitch {
//            return ActivityFragmentSwitch()
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ActivitySwitchFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            childFragmentManager.beginTransaction().apply {
                add(R.id.activity_fragment_switch_container, ActivityFragment())
                commit()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
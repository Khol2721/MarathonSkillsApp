package ru.fefu.marathonsskillsapp.main_page

//import android.content.Intent
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Toast
//import androidx.lifecycle.ViewModelProvider
//import marathonsskillsapp.R
//import marathonsskillsapp.databinding.ActivityProfileFragmentBinding

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.fefu.marathonsskillsapp.main_page.api.ProfileViewModel
import ru.fefu.marathonsskillsapp.R
import ru.fefu.marathonsskillsapp.databinding.ActivityProfileFragmentBinding
import ru.fefu.marathonsskillsapp.main_page.api.Result
import ru.fefu.marathonsskillsapp.main_page.api.User
import ru.fefu.marathonsskillsapp.welcome.WelcomeActivity

class ProfileFragment : BaseFragment<ActivityProfileFragmentBinding>(R.layout.activity_profile_fragment) {
    private var _binding: ActivityProfileFragmentBinding? = null
    override val binding get() = _binding!!
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ActivityProfileFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        binding.clickTextChangePassword.setOnClickListener {
//            val manager = activity?.supportFragmentManager?.findFragmentByTag("profileFragment")?.childFragmentManager
//            manager?.beginTransaction()?.apply {
//                manager.fragments.forEach(::hide)
//                replace(
//                    R.id.fragment_container_profile_switch,
//                    ChangePasswordFragment(),
//                    "profile_change"
//                )
//                addToBackStack(null)
//                commit()
//            }
//        }
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]


        viewModel.logoutUser
            .onEach {
                if (it is Result.Success<Unit>) {
                    App.INSTANCE.sharedPrefs.edit().remove("token").apply()
                    val intent = Intent(requireActivity(), WelcomeActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
                else if (it is Result.Errors<Unit>) {
                    Toast.makeText(requireContext(), it.errors.toString(), Toast.LENGTH_LONG).show()
                }
            }
            .launchIn(lifecycleScope)

        viewModel.profile
            .onEach {
                if (it is Result.Success<User>) {
                    binding.loginProfile.text = it.result.login
                    binding.nameProfile.text = it.result.name
                    binding.surnameProfile.text = it.result.surname
                }
                else if (it is Result.Errors<User>) {
                    Toast.makeText(requireContext(), it.errors.toString(), Toast.LENGTH_LONG).show()
                    println(it.errors.toString())
                }
            }
            .launchIn(lifecycleScope)
        viewModel.getProfile()

        binding.buttonExit.setOnClickListener {
            viewModel.logout()
        }
    }
}
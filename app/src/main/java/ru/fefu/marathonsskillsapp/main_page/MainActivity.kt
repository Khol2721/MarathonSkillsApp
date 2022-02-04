package ru.fefu.marathonsskillsapp.main_page

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.fefu.marathonsskillsapp.R
import ru.fefu.marathonsskillsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().apply {
                add(
                    R.id.fragmentContainerView,
                    ActivityFragmentSwitch(),
                    "activityFragment"
                )
                commit()
            }
        }

        binding.bottomNav.setOnNavigationItemSelectedListener {
            val activityFragment = supportFragmentManager.findFragmentByTag("activityFragment")
            val profileFragment = supportFragmentManager.findFragmentByTag("profileFragment")
            when (it.itemId) {
                R.id.activity -> {
                    if (profileFragment !== null) {
                        supportFragmentManager.beginTransaction().hide(profileFragment).commit()
                    }
                    if (activityFragment !== null) {
                        supportFragmentManager.beginTransaction().show(activityFragment).commit()
                    }
                }

                R.id.profile -> {
                    if (activityFragment !== null) {
                        supportFragmentManager.beginTransaction().hide(activityFragment).commit()
                    }
                    if (profileFragment !== null) {
                        supportFragmentManager.beginTransaction().show(profileFragment).commit()
                    } else {
                        supportFragmentManager.beginTransaction().apply {
                            add(
                                R.id.fragmentContainerView,
                                ProfileFragmentSwitch(),
                                "profileFragment"
                            )
                            commit()
                        }
                    }
                }
            }
            true
        }
    }

    override fun onBackPressed() {
        val active = supportFragmentManager.fragments.firstOrNull{!it.isHidden}!!
        val childManager = active.childFragmentManager

        if (childManager.backStackEntryCount != 0) {
            childManager.popBackStack()
        }

        else if (supportFragmentManager.backStackEntryCount != 0) {
            supportFragmentManager.popBackStack()
        }

        else {
            super.onBackPressed()
        }
    }
}

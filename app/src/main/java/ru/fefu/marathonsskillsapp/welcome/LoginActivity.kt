package ru.fefu.marathonsskillsapp.welcome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import marathonsskillsapp.R
import ru.fefu.marathonsskillsapp.main_page.MainActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun back (view: View) {
        finish()
    }

    fun go_to_main (view: View) {
        val mn = Intent(this, MainActivity::class.java)
        startActivity(mn)
    }
}
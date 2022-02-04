//package ru.fefu.marathonsskillsapp.welcome
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.view.View
//import marathonsskillsapp.R
//
//class WelcomeActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_welcome)
//
//
//    }
//
//    fun registrationGo (view: View) {
//        val reg = Intent(this, RegistrationActivity::class.java)
//        startActivity(reg)
//    }
//
//    fun loginGo (view: View) {
//        val log = Intent(this, LoginActivity::class.java)
//        startActivity(log)
//    }
//
//}

package ru.fefu.marathonsskillsapp.welcome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import ru.fefu.marathonsskillsapp.main_page.App
import ru.fefu.marathonsskillsapp.R
import ru.fefu.marathonsskillsapp.main_page.MainActivity

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        if (App.INSTANCE.sharedPrefs.getString("token", null) !== null) {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val btnRegistration = findViewById<Button>(R.id.registrationButton)
        btnRegistration.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }

        val clickText = findViewById<TextView>(R.id.loginButton)
        clickText.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}


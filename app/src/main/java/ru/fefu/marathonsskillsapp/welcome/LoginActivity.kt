//package ru.fefu.marathonsskillsapp.welcome
//
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.view.View
//import marathonsskillsapp.R
//import ru.fefu.marathonsskillsapp.main_page.MainActivity
//
//class LoginActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)
//    }
//
//    fun back (view: View) {
//        finish()
//    }
//
//    fun go_to_main (view: View) {
//        val mn = Intent(this, MainActivity::class.java)
//        startActivity(mn)
//    }
//}

package ru.fefu.marathonsskillsapp.welcome

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import ru.fefu.marathonsskillsapp.R
import ru.fefu.marathonsskillsapp.main_page.api.Token
import ru.fefu.marathonsskillsapp.main_page.App
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.fefu.marathonsskillsapp.main_page.api.LoginViewModel
import ru.fefu.marathonsskillsapp.main_page.api.Result
import ru.fefu.marathonsskillsapp.main_page.MainActivity

class LoginActivity : AppCompatActivity() {
    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this)[LoginViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel.dataFlow
            .onEach {
                if (it is Result.Success<Token>) {
                    App.INSTANCE.sharedPrefs.edit().putString("token", it.result.token).apply()
                    val intent = Intent(this, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                } else if (it is Result.Errors<Token>) {
                    Toast.makeText(this, it.errors.toString(), Toast.LENGTH_LONG).show()
                    println(it.errors.toString())
                }
            }
            .launchIn(lifecycleScope)

        val btnEnter = findViewById<Button>(R.id.buttonEnter)
        btnEnter.setOnClickListener {
            val login = findViewById<TextInputLayout>(R.id.login_entrance).editText?.text.toString()
            val password = findViewById<TextInputLayout>(R.id.password_entrance).editText?.text.toString()

            viewModel.login(login, password)
        }
    }


    fun back(view: View) {
        onBackPressed()
    }
}

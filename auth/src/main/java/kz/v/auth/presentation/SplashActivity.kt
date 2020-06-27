package kz.v.auth.presentation

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import kz.v.auth.presentation.ui.AuthActivity
import timber.log.Timber


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.i("onCreate")
        super.onCreate(savedInstanceState)
        navigateToMainScreen()
    }

    private fun navigateToMainScreen() {
        Timber.i("navigateToMainScreen")
        Handler().postDelayed({
            startActivity(Intent(this, AuthActivity::class.java))
//            AuthActivity.newIntent(context = applicationContext)
            finish()
        }, 1000.toLong())
    }
}
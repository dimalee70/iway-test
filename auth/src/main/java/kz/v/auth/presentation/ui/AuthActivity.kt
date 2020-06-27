package kz.v.auth.presentation.ui

import android.content.Context
import android.content.Intent
import kz.v.auth.R
import kz.v.ui_components.base.BaseActivity

class AuthActivity: BaseActivity(R.layout.activity_auth){

    companion object{
        fun newIntent(context: Context?) = Intent(context, AuthActivity::class.java)
    }
}
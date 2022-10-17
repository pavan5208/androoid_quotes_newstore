package com.newstore.quotes.ui.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.newstore.quotes.R
import dagger.android.support.DaggerAppCompatActivity
import java.lang.ref.WeakReference

class LoginSignUpActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_sign_up)
    }

    companion object {
        fun startActivity(weakReference: WeakReference<Activity>) {
            val intent = Intent(weakReference.get(), LoginSignUpActivity::class.java)
            weakReference.get()?.startActivity(intent)
        }
    }
}
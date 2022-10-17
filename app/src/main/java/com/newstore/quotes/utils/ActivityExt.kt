package com.newstore.quotes.utils

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.newstore.quotes.R

fun AppCompatActivity.addFragment(
    fragment: Fragment,
    tag: String,
    @IdRes containerViewID: Int
) {
    supportFragmentManager
        .beginTransaction().apply {
            setCustomAnimations(
                R.anim.slide_in_top, 0,
                0, R.anim.slide_out_bottom
            )
        }
        .add(containerViewID, fragment)
        .addToBackStack(tag).commit()
}
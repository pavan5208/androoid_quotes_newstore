package com.newstore.quotes.ui.activities

import android.os.Bundle
import com.newstore.quotes.R
import dagger.android.support.DaggerAppCompatActivity

class QuotesListActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quotes_list)
    }
}
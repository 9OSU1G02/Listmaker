package com.GOSU1902.listmaker.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.GOSU1902.listmaker.R
import com.GOSU1902.listmaker.ui.main.ui.listdetail.ListDetailFragment

class ListDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_detail)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ListDetailFragment.newInstance())
                .commitNow()
        }
    }
}
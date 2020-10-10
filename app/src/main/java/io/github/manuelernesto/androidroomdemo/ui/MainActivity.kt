package io.github.manuelernesto.androidroomdemo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.manuelernesto.androidroomdemo.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
    }
}
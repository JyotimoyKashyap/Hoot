package com.example.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(actionbar)

        val appBarConfiguration = AppBarConfiguration
            .Builder(R.id.todoListFragment)
            .build()

        setupActionBarWithNavController(findNavController(R.id.navhostfragment), appBarConfiguration)
        supportActionBar?.elevation = 0f

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.navhostfragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
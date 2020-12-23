package com.example.todoapp

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.todoapp.databinding.ActivityMainBinding
import com.google.android.material.bottomappbar.BottomAppBar


class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.actionbar)

        val appBarConfiguration = AppBarConfiguration
            .Builder(R.id.todoListFragment)
            .build()

        setupActionBarWithNavController(findNavController(R.id.navhostfragment), appBarConfiguration)
        supportActionBar?.elevation = 0f



        binding.run {
            findNavController(R.id.navhostfragment).addOnDestinationChangedListener(
                this@MainActivity
            )
        }



        binding.fab.apply {
            setHideMotionSpecResource(R.animator.fab_hide)
            setShowMotionSpecResource(R.animator.fab_show)
            setOnClickListener{
                findNavController(R.id.navhostfragment).navigate(R.id.action_todoListFragment_to_addTodoFragment)
            }
        }


    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.navhostfragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        when(destination.id){
            R.id.splashScreenFragment ->{
                binding.run {
                    fab.setImageState(intArrayOf(-android.R.attr.state_activated), true)
                    mainbottomappbar.visibility = View.GONE
                    mainbottomappbar.performHide()
                    fab.visibility = View.GONE

                }

            }
            R.id.todoListFragment ->{
                binding.run {
                    fab.setImageState(intArrayOf(-android.R.attr.state_activated), true)
                    mainbottomappbar.visibility = View.VISIBLE
                    mainbottomappbar.performShow()
                    fab.visibility = View.VISIBLE
                    mainbottomappbar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER

                }

            }
            R.id.addTodoFragment ->{
                binding.run {
                    fab.setImageState(intArrayOf(android.R.attr.state_activated), true)
                    mainbottomappbar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END

                }
            }
        }
    }


}
package com.example.todoapp

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.Toast
import android.widget.Toolbar
import androidx.customview.widget.Openable
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.todoapp.databinding.ActivityMainBinding
import com.example.todoapp.fragments.AddTodoFragment.AddTodoFragmentDirections
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.transition.MaterialFadeThrough
import com.google.android.material.transition.MaterialSharedAxis


class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding
    val drawableId = intArrayOf(R.drawable.add, R.drawable.done, R.drawable.edit)

    val currentNavigationFragment: Fragment?
        get() = supportFragmentManager.findFragmentById(R.id.navhostfragment)
            ?.childFragmentManager
            ?.fragments
            ?.first()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.actionbar)


        val appBarConfiguration = AppBarConfiguration
            .Builder(R.id.todoListFragment)
            .build()


        setupActionBarWithNavController(findNavController(R.id.navhostfragment), appBarConfiguration)
        binding.actionbar.elevation = 18.toFloat()
        customizeToolbar()







    }

    // function to customize the material toolbar
    fun customizeToolbar(){
        val radius = 18
        val background = binding.actionbar.background as MaterialShapeDrawable

        background.shapeAppearanceModel =
            background.shapeAppearanceModel.toBuilder()
                .setAllCorners(CornerFamily.ROUNDED, radius.toFloat())
                .build()

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.navhostfragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }



    private fun navigateToAdd(){
        currentNavigationFragment?.apply {
            exitTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true).apply {
                duration = 300.toLong()
            }
            reenterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false).apply {
                duration = 300.toLong()
            }
        }
        findNavController(R.id.navhostfragment).navigate(R.id.action_todoListFragment_to_addTodoFragment)
    }


}
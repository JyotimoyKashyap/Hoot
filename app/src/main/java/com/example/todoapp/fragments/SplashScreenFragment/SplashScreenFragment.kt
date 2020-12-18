package com.example.todoapp.fragments.SplashScreenFragment

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.todoapp.R


class SplashScreenFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (activity as AppCompatActivity).supportActionBar?.hide()
        val view =  inflater.inflate(R.layout.fragment_splash_screen, container, false)

        Handler(Looper.getMainLooper()).postDelayed({
            view.findNavController().navigate(R.id.action_splashScreenFragment_to_todoListFragment)
        },500)

        return view
    }


}
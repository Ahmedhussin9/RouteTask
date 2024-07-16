package com.route_task.presentation.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.route_task.R
import com.route_task.databinding.FragmentSplashBinding
import com.route_task.presentation.ui.BindingFragment
import com.route_task.util.navOptionsFromTopAnimation
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashFragment :BindingFragment<FragmentSplashBinding>(){
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentSplashBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navToHome()
    }

    private fun navToHome() {
        lifecycleScope.launch {
            delay(2000)
            findNavController().navigate(R.id.homeFragment,null, navOptions = navOptionsFromTopAnimation())
        }

    }
}
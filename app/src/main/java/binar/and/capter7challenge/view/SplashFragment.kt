package binar.and.capter7challenge.view

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.asLiveData
import androidx.navigation.findNavController
import binar.and.capter7challenge.R

class SplashFragment : Fragment() {

    lateinit var userManager: UserManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_splash, container, false)
        userManager = UserManager(requireContext())
        Handler().postDelayed({
            userManager.userLogin.asLiveData().observe(requireActivity()){
                if (it.equals("false")){
                    view.findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
                }else if (it.equals("true")){
                    view.findNavController().navigate(R.id.action_splashFragment_to_homeFragment2)
                }
            }
        }, 2000)
        return view
    }


}
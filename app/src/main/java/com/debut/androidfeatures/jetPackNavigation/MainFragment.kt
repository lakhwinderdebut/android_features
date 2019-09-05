package com.debut.androidfeatures.jetPackNavigation


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.debut.androidfeatures.R
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment(), View.OnClickListener {

    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view_A_btn.setOnClickListener(this)
        send_B_btn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.view_A_btn -> navController.navigate(R.id.action_mainFragment_to_AFragment)
            R.id.send_B_btn -> navController.navigate(R.id.action_mainFragment_to_BFragment)
        }
    }


}

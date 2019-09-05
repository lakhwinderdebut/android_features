package com.debut.androidfeatures.jetPackNavigation


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.debut.androidfeatures.R
import kotlinx.android.synthetic.main.fragment_view_balance.*


class CFragment : Fragment(), View.OnClickListener {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_view_balance, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val amount =  arguments?.getString("data").toString()
        balance_value.text = amount
    }


    override fun onClick(v: View?) {
    }

}

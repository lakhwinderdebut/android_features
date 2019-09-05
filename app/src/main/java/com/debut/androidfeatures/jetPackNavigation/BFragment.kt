package com.debut.androidfeatures.jetPackNavigation


import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.debut.androidfeatures.R
import kotlinx.android.synthetic.main.fragment_choose_recipient.*


class BFragment : Fragment(), View.OnClickListener {

    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_choose_recipient, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        next_btn.setOnClickListener(this)
        cancel_btn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.next_btn -> {
                if (!TextUtils.isEmpty(input_recipient.text?.trim())) {
                    val bundle = bundleOf("data" to input_recipient.text.toString())
                    navController.navigate(R.id.action_BFragment_to_CFragment, bundle)
                } else {
                    Toast.makeText(activity, "Enter a name", Toast.LENGTH_SHORT).show()
                }
            }

            R.id.cancel_btn -> activity?.onBackPressed()
        }
    }


}

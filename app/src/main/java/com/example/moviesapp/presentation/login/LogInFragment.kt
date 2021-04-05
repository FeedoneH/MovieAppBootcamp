package com.example.moviesapp.presentation.login

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.moviesapp.MainActivity
import com.example.moviesapp.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_log_in.*


class LogInFragment : Fragment(R.layout.fragment_log_in) {
    lateinit var logInViewModel: LogInViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logInViewModel = (activity as MainActivity).logInViewModel
        logInViewModel.logIn()
        var sessionId = (activity as MainActivity).sharedPreferences.getString("sessionId", null)

        loginBtn.setOnClickListener {
            logInViewModel.tokenId.observe(
                    viewLifecycleOwner, {

                if (it.data != null) {
                    var requestTokenId = it.data.requestToken
                    Log.i("requestTokenId", "onViewCreated: $requestTokenId")
                    var url = "https://www.themoviedb.org/authenticate/${requestTokenId}"
                    Log.i("requestTokenId", "onViewCreated: $requestTokenId")
                    var action = LogInFragmentDirections.actionLogInFragmentToAuthFragment(url, requestTokenId)
                    (activity as MainActivity).bottomNavigationView2?.menu?.findItem(R.id.accountFragment)?.isVisible = true
                    findNavController().navigate(action)

                } else Toast.makeText(context, "Error occurred", Toast.LENGTH_SHORT).show()
            })

        }
        noAccountBtn.setOnClickListener {
        if (sessionId != null) {
            logInViewModel.signOut(sessionId)
            (activity as MainActivity).sharedPreferences.edit().putString("sessionId", null).apply()
        }
            (activity as MainActivity).bottomNavigationView2.menu.findItem(R.id.accountFragment).isVisible=false
            findNavController().navigate(R.id.moviesFragment)
        }

    }

    override fun onStart() {
        super.onStart()
//        logInViewModel.logIn()
    }

    override fun onResume() {
        super.onResume()
//        logInViewModel.logIn()
    }

}
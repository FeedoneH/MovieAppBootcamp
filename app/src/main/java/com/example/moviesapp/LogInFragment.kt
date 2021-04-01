package com.example.moviesapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.moviesapp.presentation.login.LogInViewModel
import kotlinx.android.synthetic.main.fragment_log_in.*


class LogInFragment : Fragment() {
    lateinit var logInViewModel: LogInViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_log_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logInViewModel = (activity as MainActivity).logInViewModel
        loginError.visibility = View.INVISIBLE
        loginBtn.setOnClickListener {
            var email = emailEditText.text.toString()
            var password = passwordEditText.text.toString()
            logInViewModel.logIn(email, password)
            loginBtn.isEnabled = false
            logInViewModel.getCurrentUser()
            logInViewModel.currentUser.observe(viewLifecycleOwner, {
                Log.i("loginscreen", "onViewCreated: ${it.data}")
                if (it.data != null) {
                    findNavController().navigate(R.id.moviesFragment)
                }
                else{
                    setError()
                }
            })
        }
        noaccountBtn.setOnClickListener {
            findNavController().navigate(R.id.signUpFragment)
        }
    }
    fun  setError(){
        loginError.text = "Invalid email or password"
        loginError.visibility = View.VISIBLE
        loginBtn.isEnabled = true
    }
}
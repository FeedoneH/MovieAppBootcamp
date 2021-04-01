package com.example.moviesapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.moviesapp.data.model.user.User
import com.example.moviesapp.presentation.signup.SignUpViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_sign_up.*
import kotlinx.coroutines.delay
import org.w3c.dom.Text


class SignUpFragment : Fragment() {
    lateinit var signUpViewModel: SignUpViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signUpViewModel = (activity as MainActivity).signUpViewModel
        signUpViewModel.signOut()

        errorText.visibility = View.INVISIBLE
        signUpViewModel.getCurrentUser()
        continueBtn.setOnClickListener {
            (activity as MainActivity).bottomNavigationView2.menu.removeItem(R.id.accountFragment)

            findNavController().navigate(R.id.moviesFragment)
        }
        signupBtn.setOnClickListener {
            var email: String = signUpEmailText.text.toString()
            var password: String = signUpPasswordText.text.toString()
            var userName: String = signUpNameText.text.toString()
            signUpUser(email, password, userName)

            signUpViewModel.getCurrentUser()
            signUpViewModel.newUser.observe(viewLifecycleOwner, {
                Log.i("user", "onViewCreated: ${it.data}")
                if (it.data != null) {
                    var user = User(it.data.id, userName, it.data.email)
                    signUpViewModel.addUserToDB(user)
                    findNavController().navigate(R.id.moviesFragment)
                } else Toast.makeText(context, "No account", Toast.LENGTH_SHORT).show()
            })

        }
        haveaccountBtn.setOnClickListener {
            signUpViewModel.newUser.observe(viewLifecycleOwner, {
                Log.i("user", "onsignout: ${it.data}")
            })
            signUpViewModel.signOut()
        }
    }

    fun signUpUser(email: String, password: String, userName: String) {

        if (email == "" || password == "" || userName == "") {
            setError("Write your data.")


        } else {
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                setError("Write your email correctly")

            }
            if (password.length < 6) {
                setError("password should be at least 6 symbols")

            } else {
                signUpViewModel.signUp(userName, email, password)
                signupBtn.isEnabled=false
            }
        }
    }

    fun setError(error: String) {
        errorText.text = error
        errorText.visibility = View.VISIBLE
        signupBtn.isEnabled=true
    }


}
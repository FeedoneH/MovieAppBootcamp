package com.example.moviesapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.moviesapp.presentation.account.AccountViewModel
import kotlinx.android.synthetic.main.fragment_account.*

class AccountFragment : Fragment() {
    lateinit var accountViewModel: AccountViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        accountViewModel = (activity as MainActivity).accountViewModel
        accountViewModel.getAuthInfo()
        accountViewModel.authInfo.observe(viewLifecycleOwner, {
            Log.i("currentuseraccount", "account: ${it.data} ")
            var uid = it.data?.id
            if (uid != null) {
                accountViewModel.getCurrentUser(uid)
            }
        })
        accountViewModel.currentUser.observe(viewLifecycleOwner, {

            userInfoText.text = "Hello, " + it.userName
            Log.i("accountscreen", "onViewCreated: $it")
        }
        )
        signoutBtn.setOnClickListener {
            accountViewModel.signOut()
            findNavController().navigate(R.id.logInFragment)
        }
    }
}
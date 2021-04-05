package com.example.moviesapp.presentation.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

import com.example.moviesapp.MainActivity
import com.example.moviesapp.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_auth.*


class AuthFragment : Fragment() {
    lateinit var authViewModel: AuthViewModel;
    val authFragmentArgs: AuthFragmentArgs by navArgs()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_auth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        authViewModel = (activity as MainActivity).authViewModel
        val url = authFragmentArgs.url
        val tokenId = authFragmentArgs.tokenId

        setUpWebView(url)
        webView.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    var currentUrl = request!!.url.toString()
                    weblinkText.text = currentUrl ?: ""
                    view?.loadUrl(currentUrl)
                    if (currentUrl.contains("/allow")) {
                        authViewModel.getSessionId(tokenId)
                        authViewModel.sessionInfo.observe(viewLifecycleOwner, {
                            Log.i("sessionid", "shouldOverrideUrlLoading: ${it.message}")
                            if (it.data != null) {
                                if (it.data.success == true) {
                                    var idInfo = it.data
                                    (activity as MainActivity).sharedPreferences.edit().putString("sessionId", idInfo.sessionId).apply()
                        findNavController().navigate(R.id.moviesFragment)

                                }
                            }
                        })


                    }
                    if (currentUrl.contains("/deny")) {
                        (activity as MainActivity).bottomNavigationView2.menu.removeItem(R.id.accountFragment)
                        (activity as MainActivity).sharedPreferences.edit().putString("sessionId", null).apply()

                        findNavController().navigate(R.id.moviesFragment)
                    }
                    else {
                        view?.loadUrl(currentUrl)
                    }

                }
                return true


            }


        }

    }

    fun setUpWebView(url: String) {
        webView.getSettings().setLoadWithOverviewMode(true)
        val webSettings: WebSettings = webView.getSettings()
        webSettings.useWideViewPort = true
        webSettings.javaScriptEnabled = true
        webView.loadUrl(url)
    }

}
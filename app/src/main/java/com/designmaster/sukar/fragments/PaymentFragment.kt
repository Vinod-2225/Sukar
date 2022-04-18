package com.designmaster.sukar.fragments

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.Button
import com.designmaster.sukar.activities.MainActivity
import com.designmaster.sukar.R
import com.designmaster.sukar.util.AppPrefs
import com.designmaster.sukar.util.BaseFragment



class PaymentFragment : BaseFragment(), View.OnClickListener {
    var rootView: View? = null
    var savedInstanceState: Bundle? = null
    var wv: WebView? = null
    var url: String = ""
    var title: String = ""

//    var btnHome: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.savedInstanceState = savedInstanceState

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (arguments != null) {
            url = requireArguments().getString("Url").toString()
        }

//        if (arguments != null) {
//            title = requireArguments().getString("Title").toString()
//        }

        if (AppPrefs.isLocaleEnglish(activity)) {
            (activity as MainActivity?)!!.setHeaders("Payment",true)
        } else {
            (activity as MainActivity?)!!.setHeaders("Payment",true)
        }



        if (rootView == null) {
            rootView = if (AppPrefs.isLocaleEnglish(activity)) {
                inflater.inflate(R.layout.payment_fragment, container, false)
            } else {
                inflater.inflate(R.layout.payment_fragment, container, false)
            }
            idMappings()
            setOnClickListeners()

        }

        return rootView
    }

    private fun idMappings() {

//        btnHome = rootView!!.findViewById(R.id.btnHome)
        wv = rootView!!.findViewById<View>(R.id.wv) as WebView

//        btnHome!!.visibility = View.GONE


        wv!!.settings.javaScriptEnabled = true
        wv!!.settings.allowFileAccess = true
        wv!!.settings.saveFormData = false
        wv!!.settings.savePassword = false
        wv!!.settings.setRenderPriority(WebSettings.RenderPriority.HIGH)
        wv!!.settings.cacheMode = WebSettings.LOAD_NO_CACHE
        wv!!.settings.setSupportZoom(false)


        Log.v("Pay****", url!!)
        showProgress()
        wv!!.loadUrl(url!!)


        wv!!.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                val url = request?.url.toString()
                view?.loadUrl(url)
                return super.shouldOverrideUrlLoading(view, request)
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                showProgress()
                Log.v("UrlStart****", url!!)
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                hideProgress()
                Log.v("UrlFinished****",      url!!)
//                if(url.contains("https://batocare.com/demo/payment-success.php")|| url.contains("https://batocare.com/demo/payment-failure.php")){
//                    (activity as MainActivity?)!!.setHeaders(false)
////                    btnHome!!.visibility = View.VISIBLE
//                }
                super.onPageFinished(view, url)
            }

            override fun onReceivedError(view: WebView, request: WebResourceRequest, error: WebResourceError) {
                hideProgress()
                super.onReceivedError(view, request, error)
            }
        }
    }

    private fun setOnClickListeners() {

//        btnHome!!.setOnClickListener(this)

    }
    override fun onClick(v: View) {
        when (v.id) {
//            R.id. btnHome -> {
//                startActivity(
//                        Intent(
//                                activity,
//                                MainActivity::class.java
//                        )
//                )
//                activity?.finishAffinity()
//            }
        }
    }
}
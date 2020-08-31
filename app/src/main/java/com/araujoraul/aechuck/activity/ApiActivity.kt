package com.araujoraul.aechuck.activity

import android.graphics.Bitmap
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import com.araujoraul.aechuck.R
import com.araujoraul.aechuck.utils.hide
import com.araujoraul.aechuck.utils.setupToolbar
import com.araujoraul.aechuck.utils.show

class ApiActivity : BaseActivity() {

    private val URL_API = "https://api.chucknorris.io/"
    var webView: WebView? = null
    var progress: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_api)

        val mToolbar = setupToolbar(R.id.toolbar, getString(R.string.title_api))
        mToolbar.setDisplayHomeAsUpEnabled(true)

        webView = findViewById(R.id.webView)
        progress = findViewById(R.id.progress_api)

        setWebViewClient(webView)
        webView?.loadUrl(URL_API)
    }

    private fun setWebViewClient(webView: WebView?) {

        webView?.webViewClient = object : WebViewClient() {

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                progress?.show()
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                progress?.hide()
            }

        }

    }

}
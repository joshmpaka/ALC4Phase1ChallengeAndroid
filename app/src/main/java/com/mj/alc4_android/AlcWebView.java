package com.mj.alc4_android;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.http.SslError;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class AlcWebView extends AppCompatActivity {
    private WebView mWebView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        if ( isNetworkAvailable() )     //check if internet available or not
        {
            mWebView = findViewById(R.id.web);
            WebSettings webSettings = mWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            mWebView.loadUrl("https://andela.com/alc/");
            mWebView.setWebViewClient(new WebViewClient(){
                @Override
                public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                    handler.proceed();
                }
            });
        }
        else    //Not connected
        {
            Toast.makeText(
                    AlcWebView.this,
                    "Internet Disconnected",
                    Toast.LENGTH_LONG
            ).show();
        }

    }

    public boolean isNetworkAvailable()
    {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }


    @Override
    public void onBackPressed() {

        if (mWebView.canGoBack()){
            mWebView.goBack();
        }
        else
            super.onBackPressed();

    }
}

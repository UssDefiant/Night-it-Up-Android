package com.puyo.niu;


import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebView;

public class layoutMap extends Activity {
	WebView mWebView;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.map);

        mWebView = (WebView) findViewById(R.id.webview12);
        mWebView.getSettings().setJavaScriptEnabled(true);
        //mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.loadUrl("file:///android_asset/booth.html");        
    }
}
package com.puyo.niu;


import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;

public class parkingMap extends Activity {
	WebView mv;
    /** Called when the activity is first created. */

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.parking);

        mv = (WebView) findViewById(R.id.webview3);
        mv.getSettings().setJavaScriptEnabled(true);
        //mWebView.getSettings().setBuiltInZoomControls(true);
        mv.loadUrl("file:///android_asset/parking.html");        
    }
}


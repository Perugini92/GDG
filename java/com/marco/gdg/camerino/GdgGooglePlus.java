package com.marco.gdg.camerino;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class GdgGooglePlus extends Fragment {

    private WebView mWebView;
    private boolean mIsWebViewAvailable;
    private String mUrl = "https://plus.google.com/app/basic/118137135858143918205/posts?cbp=1axqp8f5hd4gq&sview=28&cid=5&soc-app=115&soc-platform=1&sparm=sc%3Dpo%26sq%3Dgdg%2Bcamerino";
    private ProgressDialog pd;
    private ConnectivityManager cm;



    /**
     * Creates a new fragment which loads the supplied url as soon as it can
     * @param url the url to load once initialised
     */



    /**
     * Called to instantiate the view. Creates and returns the WebView.
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (mWebView != null) {
            mWebView.destroy();
        }
        mWebView = new WebView(getActivity());
        mWebView.setOnKeyListener(new View.OnKeyListener(){


            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
                    mWebView.goBack();
                    return true;
                }
                return false;
            }

        });
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = cm
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo datac = cm
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if ((wifi != null & datac != null)
                && (wifi.isConnected() | datac.isConnected())) {
            //connection is avlilable
            mWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
            mWebView.loadUrl(mUrl);
            mWebView.setWebViewClient(new InnerWebViewClient()); // forces it to open in app
            mIsWebViewAvailable = true;
            WebSettings settings = mWebView.getSettings();
            WebClientClass webViewClient = new WebClientClass();
            mWebView.setWebViewClient(webViewClient);
            settings.setJavaScriptEnabled(true);
        }else{
            mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ONLY);
            mWebView.loadUrl(mUrl);
            Toast.makeText(getActivity().getApplicationContext(), "Offline", Toast.LENGTH_SHORT).show();

        }

        return mWebView;



    }

    public WebView getWebView() {
        return mIsWebViewAvailable ? mWebView : null;
    }

    /* To ensure links open within the application */
    private class InnerWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }


    }


   public class WebClientClass extends WebViewClient {
        ProgressDialog pd = null;

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            pd = new ProgressDialog(getActivity());


            //pd.setIndeterminate(true);
            pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);

            pd.setMessage("Loading Google + ...");
            pd.show();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

            pd.dismiss();
        }
    }

}

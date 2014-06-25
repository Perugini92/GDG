package com.marco.gdg.camerino;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Youtube extends Fragment {
    private static final String baseURl = "http://lug2013.altervista.org/blog/";

    /*public NewsDalSitoCache() {
        //super();
        //mUrl = url;
    }*/
    private WebView mWebView;
    private boolean mIsWebViewAvailable;
    private String mUrl = "https://m.youtube.com/channel/UCavv7njnEWTKzyzn_j5B7uw";
    private ProgressDialog pd;
    private ConnectivityManager cm;



    /**
     * Creates a new fragment which loads the supplied url as soon as it can
     * @param url the url to load once initialised
     */



    /**
     * Called to instantiate the view. Creates and returns the WebView.
     */


    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        WebView webView = new WebView(getActivity());
        webView.setClickable(true);
        webView.setFocusableInTouchMode(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://www.google.com");
        getActivity().setContentView(webView);
        return webView;

    }*/

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
            mWebView.setWebChromeClient(new WebChromeClient());
            mWebView.setWebViewClient(new InnerWebViewClient()); // forces it to open in app
            mIsWebViewAvailable = true;
            WebSettings settings = mWebView.getSettings();
            //WebClientClass webViewClient = new WebClientClass();

            //mWebView.setWebViewClient(webViewClient);
            settings.setJavaScriptEnabled(true);
        }else{
            mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ONLY);
            mWebView.loadUrl(mUrl);
        }
        /*ConnectivityManager connectionManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();

            if (networkInfo != null && networkInfo.isConnected()) {

            }
                // fetch data
                mWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
                mWebView.loadUrl(mUrl);
                mWebView.setWebViewClient(new InnerWebViewClient()); // forces it to open in app
                mIsWebViewAvailable = true;
                WebSettings settings = mWebView.getSettings();
                WebClientClass webViewClient = new WebClientClass();
                mWebView.setWebViewClient(webViewClient);
                settings.setJavaScriptEnabled(true);
            } else {
                // display error
                mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ONLY);
            }*/


        /*mWebView.setWebViewClient(new InnerWebViewClient()); // forces it to open in app
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);

        mWebView.loadUrl(mUrl);
        mIsWebViewAvailable = true;
        WebSettings settings = mWebView.getSettings();
        WebClientClass webViewClient = new WebClientClass();
        mWebView.setWebViewClient(webViewClient);
        settings.setJavaScriptEnabled(true);*/
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
    @Override
    public void onPause()
    {
        super.onPause();
        toggleWebViewState(true);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        toggleWebViewState(false);
    }

    private void toggleWebViewState(boolean pause)
    {
        try
        {
            Class.forName("android.webkit.WebView")
                    .getMethod(pause
                            ? "onPause"
                            : "onResume", (Class[]) null)
                    .invoke(mWebView, (Object[]) null);
        }
        catch (Exception e){}
    }


   public class WebClientClass extends WebViewClient {
        ProgressDialog pd = null;

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            pd = new ProgressDialog(getActivity());
            pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);

            pd.setMessage("Loading Youtube ...");
            pd.show();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            pd.dismiss();
        }
    }



        //getActivity().requestWindowFeature(Window.FEATURE_NO_TITLE);






       // }
       // return mWebView;
   // }

        /*if (mWebView != null) {
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
        mWebView.setWebViewClient(new InnerWebViewClient()); // forces it to open in app
        mWebView.loadUrl(mUrl);
        mIsWebViewAvailable = true;
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        return mWebView;



    }

    /**
     * Convenience method for loading a url. Will fail if {@link android.view.View} is not initialised (but won't throw an {@link Exception})
     * @param url
     */
   /* public void loadUrl(String url) {
        if (mIsWebViewAvailable) getWebView().loadUrl(mUrl = url);
        else Log.w("ImprovedWebViewFragment", "WebView cannot be found. Check the view and fragment have been loaded.");
    }

    /**
     * Called when the fragment is visible to the user and actively running. Resumes the WebView.
     */
   /* @Override
    public void onPause() {
        super.onPause();
        mWebView.onPause();
    }

    /**
     * Called when the fragment is no longer resumed. Pauses the WebView.
     */
   /* @Override
    public void onResume() {
        mWebView.onResume();
        super.onResume();
    }

    /**
     * Called when the WebView has been detached from the fragment.
     * The WebView is no longer available after this time.
     */
   /* @Override
    public void onDestroyView() {
        mIsWebViewAvailable = false;
        super.onDestroyView();
    }

    /**
     * Called when the fragment is no longer in use. Destroys the internal state of the WebView.
     */
 /*   @Override
    public void onDestroy() {
        if (mWebView != null) {
            mWebView.destroy();
            mWebView = null;
        }
        super.onDestroy();
    }

    /**
     * Gets the WebView.
     */
  /*  public WebView getWebView() {
        return mIsWebViewAvailable ? mWebView : null;
    }*/

    /* To ensure links open within the application */
   /* private class InnerWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }*/


  //  }
}

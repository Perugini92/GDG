package com.marco.gdg.camerino;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

//import android.app.Fragment;

/**
 * Created by Marco on 10/05/14.
 */
public class TwitterEIO extends Fragment {
    private WebView mWebView;
    private boolean mIsWebViewAvailable;

    public static final String TAG = "TimeLineActivity";

    private static final String baseURl = "https://twitter.com";

    private static final String widgetInfo = "<a class=\"twitter-timeline\" href=\"https://twitter.com/search?q=+%23IOCamerino\" data-widget-id=\"481123254229147649\"></a>" +
            "<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id))\n" +
            "{js=d.createElement(s);js.id=id;js.src=p+\"://platform.twitter.com/widgets.js\";fjs.parentNode.insertBefore(js,fjs);}}(document,\"script\",\"twitter-wjs\");</script>";

    public TwitterEIO(){}

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
        ConnectivityManager connectionManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            // fetch data
            mWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
           // mWebView.loadUrl(mUrl);
            mWebView.loadDataWithBaseURL(baseURl, widgetInfo, "text/html", "UTF-8", null);
            mWebView.setWebViewClient(new InnerWebViewClient()); // forces it to open in app
            mIsWebViewAvailable = true;
            WebSettings settings = mWebView.getSettings();
            WebClientClass webViewClient = new WebClientClass();
            mWebView.setWebViewClient(webViewClient);
            mWebView.setBackgroundColor(Color.parseColor("#C0C0C0"));

            settings.setJavaScriptEnabled(true);
        } else {
            // display error
            mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            mWebView.loadDataWithBaseURL(baseURl, widgetInfo, "text/html", "UTF-8", null);

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

      /*  View rootView = inflater.inflate(R.layout.fragment_community, container, false);


        load_background_color();

        WebView webView = (WebView)rootView.findViewById(R.id.webView);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.loadDataWithBaseURL(baseURl, widgetInfo, "text/html", "UTF-8", null);
        return rootView;*/
      public class WebClientClass extends WebViewClient {
          ProgressDialog pd = null;

          @Override
          public void onPageStarted(WebView view, String url, Bitmap favicon) {
              super.onPageStarted(view, url, favicon);
              pd = new ProgressDialog(getActivity());


              //pd.setIndeterminate(true);
              pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);

              pd.setMessage("Loading Twitter ...");
              pd.show();
          }

          @Override
          public void onPageFinished(WebView view, String url) {
              super.onPageFinished(view, url);

              pd.dismiss();
          }
      }
}


    //private void load_background_color() {

        //WebView webView = (WebView)findViewById(R.id.webView);
        //webView.setBackgroundColor(getResources().getColor(R.color.twitter_dark));
        //webView.setBackgroundColor(0); // transparent
   // }
//}

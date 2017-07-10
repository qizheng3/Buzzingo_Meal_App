package com.raywenderlich.alltherecipes;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import static com.raywenderlich.alltherecipes.R.id.button;

public class Login extends AppCompatActivity {
    private WebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        WebViewClient yourWebClient = new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                mWebView.loadUrl("javascript:HtmlViewer.showHTML" +
                        "('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>');");
            }
        };
        mWebView = (WebView) findViewById(R.id.detail_web_view1);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.setWebViewClient(yourWebClient);
        mWebView.loadUrl("https://login.gatech.edu/cas/login");

        mWebView.addJavascriptInterface(new MyJavaScriptInterface(this), "HtmlViewer");
    }
    public class MyJavaScriptInterface {

        private Context ctx;
        MyJavaScriptInterface(Context ctx) {
            this.ctx = ctx;
        }

        @JavascriptInterface
        public void showHTML(String html) {
            System.out.println(html);
            if (html.contains("successful"))
            {
                // 2
                Intent detailIntent = new Intent(ctx, MainActivity.class);
                startActivity(detailIntent);
            }
        }

    }
}

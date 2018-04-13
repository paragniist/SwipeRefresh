package com.example.vinove.swiperefresh;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    SwipeRefreshLayout swipeRefreshLayout;
    WebView webView;
    WebViewClient webViewClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout = findViewById(R.id.swipeRefresh_id);
        webView = findViewById(R.id.webView_id);

        webView.loadUrl("https://www.google.co.in");
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                webView.reload();

            }
        });

        webViewClient = new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {

                System.out.println("onPageFinished");
                swipeRefreshLayout.setRefreshing(false);// which dismisses the swipeRefreshLayout
            }


            @Override
            public void onPageCommitVisible(WebView view, String url) {
                super.onPageCommitVisible(view, url);
                //System.out.println("onPageCommitVisible");
                swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorLoadRefresh));
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
                //System.out.println("onLoadResource");
                swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorFinishRefresh));
            }
        };
        webView.setWebViewClient(webViewClient);


    }
}

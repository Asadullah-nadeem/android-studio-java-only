package razi.constructor.recyclerviewpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class CalculatorActivity extends AppCompatActivity {

    WebView simpleWebView;
    String url="https://www.goldrate24.com/gold-calculator/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        simpleWebView=(WebView) findViewById(R.id.webView);
        simpleWebView.getSettings().setJavaScriptEnabled(true);
        simpleWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        new MyAsynTask().execute();

    }

    private class MyAsynTask extends AsyncTask<Void, Void, Document> {
        @Override
        protected Document doInBackground(Void... voids) {

            Document document = null;
            try {
                document = Jsoup.connect(url).get();
                document.getElementsByClass("header").remove();
                document.getElementById("sidebar").remove();
                document.getElementById("comments").remove();

                document.getElementsByClass("comments section").remove();

                document.getElementsByAttribute("iframe").remove();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return document;
        }

        @Override
        protected void onPostExecute(Document document) {
            super.onPostExecute(document);
            simpleWebView.loadDataWithBaseURL(url, document.toString(), "text/html", "utf-8", "");
            simpleWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

            simpleWebView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    view.loadUrl(url);
                    return super.shouldOverrideUrlLoading(view, request);
                }
            });
        }
    }

}
package com.example.toshokan_manga;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.InputDevice;
import android.view.View;
import android.webkit.WebChromeClient;
import android.widget.ProgressBar;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import retrofit2.http.HTTP;

public class WebView extends AppCompatActivity {
    private PDFView webView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        final String url = getIntent().getStringExtra("U");
        String finalurl = "https://drive.google.com/viewerng/viewer?url=" + url;

        webView =  findViewById(R.id.webView);
        progressBar = findViewById(R.id.webprogress);
        progressBar.setVisibility(View.VISIBLE);


        new Retrivepdf().execute(url);

    }

    class Retrivepdf extends AsyncTask<String,Void, InputStream>{

        @Override
        protected InputStream doInBackground(String... strings) {

            InputStream inputStream=null;
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                if (urlConnection.getResponseCode()==200){
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }
            }catch (IOException e){
                return  null;
            }
            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            webView.fromStream(inputStream).load();

            progressBar.setVisibility(View.GONE);
        }
    }
}

package com.example.heroesapiretrofit;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import Url.Url;

public class LoadImgStrictModeActivity extends AppCompatActivity {
    private ImageView imgProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_img_strict_mode);
        imgProfile = findViewById(R.id.imgPhoto);
        // loadFromURL();
        loadFromURLStrictMode();
    }


    private void StrictMode() {
        android.os.StrictMode.ThreadPolicy policy =
                new android.os.StrictMode.ThreadPolicy.Builder().permitAll().build();
        android.os.StrictMode.setThreadPolicy(policy);
    }

    private void loadFromURLStrictMode() {
        StrictMode();
        try {
            // String imgURL = "https://www.gstatic.com/webp/gallery3/1.sm.png";
            String imgURL = Url.BASE_URL + "uploads/imageFile-1557891346577.jpg";
            URL url = new URL(imgURL);
            imgProfile.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
        } catch (IOException e) {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadFromURL() {
        //String url = "https://www.gstatic.com/webp/gallery3/1.sm.png";
        String url = Url.BASE_URL + "uploads/imageFile-1557891346577.jpg";
        new AsyncTaskLoadImage(imgProfile).execute(url);
    }

    public class AsyncTaskLoadImage extends AsyncTask<String, String, Bitmap> {
        private final static String TAG = "AsyncTaskLoadImage";
        private ImageView imageView;

        public AsyncTaskLoadImage(ImageView imageView) {
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap = null;
            try {
                URL url = new URL(params[0]);
                bitmap = BitmapFactory.decodeStream((InputStream) url.getContent());
            } catch (IOException e) {
                Log.e(TAG, e.getMessage());
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imageView.setImageBitmap(bitmap);
        }
    }
}
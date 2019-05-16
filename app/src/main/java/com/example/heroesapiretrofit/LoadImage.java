package com.example.heroesapiretrofit;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import ApiCall.HeroesAPI;
import Url.Url;
import model.Heroes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import strictmode.StrictMode;

public class LoadImage extends AppCompatActivity {

    private TextView tvData;
    private ImageView imgProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_image);
        imgProfile = findViewById(R.id.imgPhoto);
        tvData = findViewById(R.id.tvData);
        loadFromURL();
    }

    private void StrictMode()
    {
        android.os.StrictMode.ThreadPolicy policy =
                new android.os.StrictMode.ThreadPolicy.Builder().permitAll().build();
        android.os.StrictMode.setThreadPolicy(policy);
    }

    private void loadFromURL() {
       StrictMode();
        try {
            //String imgPath = "https://www.gstatic.com/webp/gallery3/1.sm.png";
            String imgPath = "http://10.0.2.2:3000/uploads/imageFile-1557891346577.jpg";
            URL url = new URL(imgPath);
            imgProfile.setImageBitmap(BitmapFactory.decodeStream((InputStream)url.getContent()));
        } catch (IOException e) {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadImage() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Url.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HeroesAPI heroesAPI = retrofit.create(HeroesAPI.class);

        Call<List<Heroes>> heroesCall = heroesAPI.getAllHeroes();

        heroesCall.enqueue(new Callback<List<Heroes>>() {
            @Override
            public void onResponse(Call<List<Heroes>> call, Response<List<Heroes>> response) {

                if(!response.isSuccessful())
                {
                    Toast.makeText(LoadImage.this, "Error " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Heroes> heroes = response.body();


                Log.d("My msg", "onResponse: " + response.body());
//                for(Heroes hero : heroes)
//                {
//                    String content = "";
//                    content +=hero.getImage();
//
//                    tvData.append(content);
//
//                }
            }

            @Override
            public void onFailure(Call<List<Heroes>> call, Throwable t) {
                Toast.makeText(LoadImage.this, "Error : " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                tvData.setText(t.getLocalizedMessage());
            }
        });
    }
}

package com.example.heroesapiretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ApiCall.HeroesAPI;
import Url.Url;
import model.Heroes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tvData = findViewById(R.id.tvData);
        // Insert();
        //  Load();
        // InsertEncoding();
    }

    private void Load() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Url.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HeroesAPI heroesAPI = retrofit.create(HeroesAPI.class);

        Call<List<Heroes>> listCall = heroesAPI.getAllHeroes();

        listCall.enqueue(new Callback<List<Heroes>>() {
            @Override
            public void onResponse(Call<List<Heroes>> call, Response<List<Heroes>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Heroes> heroes = response.body();

                for (Heroes heroe : heroes) {

                    String content = "";
                    content = " id " + heroe.getName();

                    tvData.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Heroes>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error  " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                tvData.setText(t.getLocalizedMessage());
            }
        });
    }
    private void InsertEncoding() {
        Heroes heroes = new Heroes("asd", "asd", "asd.jpg");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Url.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        Map<String, String> map = new HashMap<>();
        map.put("name", "sujitg");
        map.put("desc", "sujasdasdasditg");
        map.put("image", "adasdasd");
        HeroesAPI heroesAPI = retrofit.create(HeroesAPI.class);

        //  Call<Void> heroesCall = heroesAPI.registerHero("kiranaaaaa","asdasdasd","345345.jpg");
        Call<Void> heroesCall = heroesAPI.registerHero(map);
        heroesCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MainActivity.this, "" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void Insert() {
        Heroes heroes = new Heroes("asd", "asd", "asd.jpg");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Url.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HeroesAPI heroesAPI = retrofit.create(HeroesAPI.class);

        Call<Void> heroesCall = heroesAPI.registerHero(heroes);

        heroesCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MainActivity.this, "" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
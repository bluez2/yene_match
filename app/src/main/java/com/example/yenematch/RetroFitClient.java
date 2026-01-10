package com.example.yenematch;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// Changed "RetrofitClient" to "RetroFitClient" to match the filename
public class RetroFitClient {
    private static final java.lang.String BASE_URL = "http://10.0.2.2/yene_match_api/"; // 10.0.2.2 is localhost for the emulator
    private static Retrofit retrofit = null;

    public static ApiInterface getInterface() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ApiInterface.class);
    }
}


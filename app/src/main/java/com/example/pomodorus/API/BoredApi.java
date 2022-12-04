package com.example.pomodorus.API;

import retrofit2.Call;
import retrofit2.http.GET;

//interface for the API
public interface BoredApi {

    @GET("api/activity?minaccessibility=0&maxaccessibility=0.1")
    Call<Bored> getBored();
}

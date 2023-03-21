package com.example.dictionaryaiandroid.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OpenAIEndpoint {
    @POST("v1/engines/{engine}/translate")
    Call<TranslationResponse> translate(
            @Path("engine") String engine,
            @Body TranslationRequest request,
            @Header("Content-Type") String contentType,
            @Header("Authorization") String authorization
    );
}


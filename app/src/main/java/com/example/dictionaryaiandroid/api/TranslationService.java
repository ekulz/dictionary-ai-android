package com.example.dictionaryaiandroid.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TranslationService {
    public void translateText(String text, String targetLanguageCode, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String apiKey = prefs.getString("API_KEY", "");

        OpenAIEndpoint endpoint = ApiClient.getClient().create(OpenAIEndpoint.class);
        
        String contentType = "application/json";
        String authorization = "Bearer " + apiKey;

        // Create the request body
        TranslationRequest request = new TranslationRequest(text, targetLanguageCode);

        // Make the API request
        Call<TranslationResponse> call = endpoint.translate("davinci", request, contentType, authorization);
        call.enqueue(new Callback<TranslationResponse>() {
            @Override
            public void onResponse(Call<TranslationResponse> call, Response<TranslationResponse> response) {
                if (response.isSuccessful()) {
                    TranslationResponse translationResponse = response.body();
                    // Handle the translated text
                } else {
                    // Handle the error
                }
            }

            @Override
            public void onFailure(Call<TranslationResponse> call, Throwable t) {
                // Handle the error
            }
        });
    }

}

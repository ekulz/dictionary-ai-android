package com.example.dictionaryaiandroid.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Response;

public class TranslationService {
    public interface Callback {
        void onSuccess(TranslationResponse translationResponse);

        void onFailure(Throwable t);
    }

    public void translateText(TranslationRequest request, Callback callback, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String apiKey = prefs.getString("API_KEY", "");

        OpenAIEndpoint endpoint = ApiClient.getClient().create(OpenAIEndpoint.class);

        String contentType = "application/json";
        String authorization = "Bearer " + apiKey;

        // Make the API request
        Call<TranslationResponse> call = endpoint.translate("davinci", request, contentType, authorization);
        call.enqueue(new retrofit2.Callback<TranslationResponse>() {
            @Override
            public void onResponse(Call<TranslationResponse> call, Response<TranslationResponse> response) {
                if (response.isSuccessful()) {
                    TranslationResponse translationResponse = response.body();
                    callback.onSuccess(translationResponse);
                } else {
                    String code = String.valueOf(response.code());
                    Log.e("translateText", code);
                    callback.onFailure(new Exception("Failed to translate text with error: " + code));
                }
            }

            @Override
            public void onFailure(Call<TranslationResponse> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }
}

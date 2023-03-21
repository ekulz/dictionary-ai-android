package com.example.dictionaryaiandroid.api;

import com.google.gson.annotations.SerializedName;

public class TranslationRequest {
    @SerializedName("text")
    private String text;

    @SerializedName("target")
    private String targetLanguage;

    public TranslationRequest(String text, String targetLanguage) {
        this.text = text;
        this.targetLanguage = targetLanguage;
    }
}

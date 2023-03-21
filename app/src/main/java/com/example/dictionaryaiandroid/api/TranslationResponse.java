package com.example.dictionaryaiandroid.api;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class TranslationResponse {
    @SerializedName("translations")
    private List<Translation> translations;

    public void setTranslations(List<Translation> translations) {
        this.translations = translations;
    }

    public String getTranslatedText() {
        if (translations != null && !translations.isEmpty()) {
            return translations.get(0).getTranslatedText();
        } else {
            return null;
        }
    }

    private static class Translation {
        @SerializedName("translated_text")
        private String translatedText;

        public String getTranslatedText() {
            return translatedText;
        }

        public void setTranslatedText(String translatedText) {
            this.translatedText = translatedText;
        }
    }
}


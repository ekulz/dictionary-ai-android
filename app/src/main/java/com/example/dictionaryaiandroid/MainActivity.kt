package com.example.dictionaryaiandroid

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.dictionaryaiandroid.api.TranslationRequest
import com.example.dictionaryaiandroid.api.TranslationResponse
import com.example.dictionaryaiandroid.api.TranslationService

class MainActivity : AppCompatActivity() {

    private lateinit var translationService: TranslationService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the TranslationService
        translationService = TranslationService()

        // Get references to the views
        val inputEditText = findViewById<EditText>(R.id.input_text)
        val outputTextView = findViewById<TextView>(R.id.translated_text)
        val translateButton = findViewById<Button>(R.id.translate_button)
        val languageSpinner = findViewById<Spinner>(R.id.language_spinner)
        languageSpinner.setSelection(0)

        // Set a click listener on the Button view
        translateButton.setOnClickListener {
            val inputText = inputEditText.text.toString()

            // Create a TranslationRequest object
            val request = TranslationRequest(inputText, "Mandarin")

            // Call the translateText method of the TranslationService
            translationService.translateText(request, object : TranslationService.Callback {
                override fun onSuccess(translationResponse: TranslationResponse) {
                    // Update the UI with the translated text
                    outputTextView.text = translationResponse.translatedText
                }

                override fun onFailure(t: Throwable) {
                    // Handle the failure
                    Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
                }
            }, this)
        }
    }
}

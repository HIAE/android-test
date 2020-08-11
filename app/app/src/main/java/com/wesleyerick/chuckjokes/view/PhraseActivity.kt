package com.wesleyerick.chuckjokes.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wesleyerick.chuckjokes.R
import kotlinx.android.synthetic.main.activity_phrase.*

class PhraseActivity : AppCompatActivity() {

    var phrase = String()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phrase)

        phrase = intent.getStringExtra("PHRASE")

        textPhrase.text = phrase

        btnBack.setOnClickListener {
            finish()
        }
    }
}
package com.blackstone.coroutinesdebounce

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    private val sendText: StringBuilder = StringBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpEditText()
    }

    private fun setUpEditText() {
        lifecycleScope.launchWhenResumed {
            editText.asFlow().debounce(300).collect { text ->
                setText(text)
            }
        }
    }

    private fun setText(text: String) {
        sendText.append("\n")
        sendText.append(text)

        textView.text = sendText
    }
}
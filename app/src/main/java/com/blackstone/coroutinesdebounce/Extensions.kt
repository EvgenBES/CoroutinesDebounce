package com.blackstone.coroutinesdebounce

import android.text.Editable
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

/**
 * @author Evgeny Butov
 * @created 14.07.2020
 */

fun EditText.asFlow() = callbackFlow {
    val afterTextChanged: (Editable?) -> Unit = { text ->
        offer(text.toString())
    }

    val textChangedListener =
        addTextChangedListener(afterTextChanged = afterTextChanged)

    awaitClose {
        removeTextChangedListener(textChangedListener)
    }

}
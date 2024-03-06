package com.miso.uxui.ialarmed.ui.take

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TakeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is take Fragment"
    }
    val text: LiveData<String> = _text
}
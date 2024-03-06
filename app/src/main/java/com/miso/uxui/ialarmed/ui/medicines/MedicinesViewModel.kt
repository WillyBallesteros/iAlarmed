package com.miso.uxui.ialarmed.ui.medicines

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MedicinesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "LISTADO DE MEDICAMENTOS"
    }
    val text: LiveData<String> = _text
}
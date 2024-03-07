package com.miso.uxui.ialarmed.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Hola estimado monitor. \n\n" +
                "En esta aplicación encontrará dos flujos de la aplicación: \n\n" +
                "1. - Confirmar Medicamento \n" +
                "2. - Medicamentos\n\n\n" +
                "Puede acceder a ellos desde el menu de la aplicación situado en la parte superior izquierda.\n\n" +
                "Muchas Gracias por su tiempo."
    }
    val text: LiveData<String> = _text
}
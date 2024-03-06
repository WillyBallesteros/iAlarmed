package com.miso.uxui.ialarmed.models

data class Medicine(
    val id: Int,
    val nombre: String,
    val nombreCientifico: String,
    val descripcion: String,
    val indicaciones: String,
    val modoIngesta: String
)

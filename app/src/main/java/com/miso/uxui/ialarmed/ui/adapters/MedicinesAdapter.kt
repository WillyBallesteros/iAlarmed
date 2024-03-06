package com.miso.uxui.ialarmed.ui.adapters

import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.miso.uxui.ialarmed.models.Medicine
import com.miso.uxui.ialarmed.R

class MedicinesAdapter(
    private val medicinesList: List<Medicine>,
    private val onItemClicked: (Medicine) -> Unit
) : RecyclerView.Adapter<MedicinesAdapter.MedicineViewHolder>() {

    class MedicineViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val medicineNameTextView: TextView = view.findViewById(R.id.tvMedicineName)
        val viewDetailsButton: TextView = view.findViewById(R.id.btnViewDetails)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_medicine, parent, false)
        return MedicineViewHolder(view)
    }

    override fun onBindViewHolder(holder: MedicineViewHolder, position: Int) {
        val medicine = medicinesList[position]
        holder.medicineNameTextView.text = medicine.nombre
        holder.viewDetailsButton.setOnClickListener {
            onItemClicked(medicine)
        }
    }

    override fun getItemCount(): Int {
        return medicinesList.size
    }
}
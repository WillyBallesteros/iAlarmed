package com.miso.uxui.ialarmed.ui.medicines

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.miso.uxui.ialarmed.databinding.FragmentMedicinesBinding
import com.miso.uxui.ialarmed.models.Medicine
import com.miso.uxui.ialarmed.ui.adapters.MedicinesAdapter

class MedicinesFragment : Fragment() {

    private var _binding: FragmentMedicinesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val medicinesViewModel =
            ViewModelProvider(this).get(MedicinesViewModel::class.java)

        _binding = FragmentMedicinesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val medicinesList = listOf(
            Medicine(1, "Ibuprofeno", "Ibuprofenum", "Descripción...", "Indicaciones...", "Oral"),
            Medicine(2, "Paracetamol", "Paracetamolum", "Descripción...", "Indicaciones...", "Oral"),
            Medicine(3, "Aspirina", "Acetylsalicylic Acid", "Descripción...", "Indicaciones...", "Oral")
        )

        // Set up RecyclerView
        binding.rvMedicines.layoutManager = LinearLayoutManager(requireContext())
        binding.rvMedicines.adapter = MedicinesAdapter(medicinesList) { selectedMedicine ->
            // Here, implement navigation to the MedicineDetailFragment with the selected medicine
            // For example, using NavController:
            // val action = MedicinesFragmentDirections.actionMedicinesFragmentToMedicineDetailFragment(selectedMedicine.id)
            // findNavController().navigate(action)
        }

        medicinesViewModel.text.observe(viewLifecycleOwner) {
            binding.textMedicines.text = it
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
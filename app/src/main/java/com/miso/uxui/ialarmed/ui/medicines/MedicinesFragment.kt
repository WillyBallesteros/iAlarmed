package com.miso.uxui.ialarmed.ui.medicines

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.miso.uxui.ialarmed.R
import com.miso.uxui.ialarmed.databinding.FragmentMedicinesBinding
import com.miso.uxui.ialarmed.models.Medicine
import com.miso.uxui.ialarmed.ui.adapters.MedicinesAdapter

class MedicinesFragment : Fragment() {

    private var _binding: FragmentMedicinesBinding? = null
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
            Medicine(1,
                "Ibuprofeno",
                "Ibuprofenum",
                "Medicamento antiinflamatorio no esteroideo (AINE) que se utiliza para tratar el dolor leve a moderado y ayuda a aliviar los síntomas de la artritis (osteoartritis, artritis reumatoide o artritis juvenil), como la inflamación, hinchazón, rigidez y dolor articular. Este medicamento no cura la artritis y solo te ayudará mientras lo estés tomando.",
                "Utilizar para reducir la fiebre y para aliviar dolores menores y dolores de diversas condiciones, como dolores de cabeza, dolor dental, cólicos menstruales, dolores musculares o artritis",
                "Oral"),
            Medicine(2, "Paracetamol",
                "Paracetamolum",
                "Es utilizado para aliviar el dolor y reducir la fiebre en pacientes. No se vuelve adictivo cuando se toma por mucho tiempo",
                "Puede causar otros efectos no deseados cuando se toma en dosis grandes, incluyendo daño hepático serio",
                "Oral"),
            Medicine(3, "Aspirina",
                "Acetylsalicylic Acid",
                "Medicamento que pertenece al grupo de los salicilatos y se utiliza comúnmente como analgésico para dolores leves y moderados, antipirético para la fiebre y como antiinflamatorio. Es conocido por su capacidad de reducir el riesgo de coágulos de sangre, lo que puede ayudar a prevenir ataques cardíacos y accidentes cerebrovasculares.",
                "La aspirina puede causar efectos secundarios, como irritación del estómago, hemorragias estomacales y úlceras, especialmente cuando se toma en dosis altas o durante períodos prolongados.",
                "Oral")
        )

        // Set up RecyclerView
        binding.rvMedicines.layoutManager = LinearLayoutManager(requireContext())
        binding.rvMedicines.adapter = MedicinesAdapter(medicinesList) { medicine ->
            val bundle = Bundle()
            bundle.putInt("medicineId", medicine.id)
            val navController = findNavController()
            navController.navigate(R.id.nav_medicine_detail, bundle)
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

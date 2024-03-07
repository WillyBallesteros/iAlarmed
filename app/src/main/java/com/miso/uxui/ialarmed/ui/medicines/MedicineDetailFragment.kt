package com.miso.uxui.ialarmed.ui.medicines

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.miso.uxui.ialarmed.databinding.FragmentMedicineDetailBinding
import com.miso.uxui.ialarmed.models.Medicine

private const val ARG_PARAM1 = "medicineId"
class MedicineDetailFragment : Fragment() {

    private var param1: Int = 0
    private var _binding: FragmentMedicineDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMedicineDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
        }

        val medicine = findMedicineById(param1)

        binding.tvMedicineName.text = medicine.nombre
        binding.tvMedicineScientificName.text = formatTextWithBoldLabel(medicine.nombreCientifico)
        binding.tvMedicineDescription.text = formatTextWithBoldLabel(medicine.descripcion)
        binding.tvMedicineIndications.text = formatTextWithBoldLabel(medicine.indicaciones)
        binding.tvMedicineDosage.text = formatTextWithBoldLabel(medicine.modoIngesta)

        binding.btnReturn.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun findMedicineById(medicineId: Int): Medicine {
        val medicinesList = listOf(
            Medicine(1,
                "IBUPROFENO",
                "Nombre Científico:  Ibuprofenum",
                "Descripción del Medicamento:  Medicamento antiinflamatorio no esteroideo (AINE) que se utiliza para tratar el dolor leve a moderado y ayuda a aliviar los síntomas de la artritis (osteoartritis, artritis reumatoide o artritis juvenil), como la inflamación, hinchazón, rigidez y dolor articular. Este medicamento no cura la artritis y solo te ayudará mientras lo estés tomando.",
                "Indicaciones:  Descripción del Medicamento: Utilizar para reducir la fiebre y para aliviar dolores menores y dolores de diversas condiciones, como dolores de cabeza, dolor dental, cólicos menstruales, dolores musculares o artritis",
                "Modo de Ingesta:  Oral"),
            Medicine(2, "PARACETAMOL",
                "Nombre Científico:  Paracetamolum",
                "Descripción del Medicamento:  Es utilizado para aliviar el dolor y reducir la fiebre en pacientes. No se vuelve adictivo cuando se toma por mucho tiempo",
                "Indicaciones:  Descripción del Medicamento: Puede causar otros efectos no deseados cuando se toma en dosis grandes, incluyendo daño hepático serio",
                "Modo de Ingesta:  Oral"),
            Medicine(3, "ASPIRINA",
                "Nombre Científico:  Acetylsalicylic Acid",
                "Descripción del Medicamento:  Descripción del Medicamento: Medicamento que pertenece al grupo de los salicilatos y se utiliza comúnmente como analgésico para dolores leves y moderados, antipirético para la fiebre y como antiinflamatorio. Es conocido por su capacidad de reducir el riesgo de coágulos de sangre, lo que puede ayudar a prevenir ataques cardíacos y accidentes cerebrovasculares.",
                "Indicaciones:  La aspirina puede causar efectos secundarios, como irritación del estómago, hemorragias estomacales y úlceras, especialmente cuando se toma en dosis altas o durante períodos prolongados.",
                "Modo de Ingesta:  Oral")
        )
        return medicinesList.firstOrNull { it.id == medicineId } ?: throw IllegalArgumentException("Medicine not found")
    }

    fun formatTextWithBoldLabel(text: String): SpannableString {
        val spannableString = SpannableString(text)
        val delimiterIndex = text.indexOf(":")

        if (delimiterIndex != -1) {
            spannableString.setSpan(
                StyleSpan(Typeface.BOLD),
                0,
                delimiterIndex + 1,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE
            )
        }

        return spannableString
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
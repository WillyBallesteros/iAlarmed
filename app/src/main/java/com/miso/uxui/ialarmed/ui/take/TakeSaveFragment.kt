package com.miso.uxui.ialarmed.ui.take

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.miso.uxui.ialarmed.MainActivity
import com.miso.uxui.ialarmed.R
import com.miso.uxui.ialarmed.databinding.FragmentTakeConfirmBinding
import com.miso.uxui.ialarmed.databinding.FragmentTakeSaveBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TakeSaveFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TakeSaveFragment : Fragment(R.layout.fragment_take_save) {

    private var _binding: FragmentTakeSaveBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTakeSaveBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configurar OnClickListener para el botón
        binding.menuButton.setOnClickListener {
            // Crear un Intent para iniciar MainActivity
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
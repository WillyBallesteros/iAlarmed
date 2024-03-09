package com.miso.uxui.ialarmed.ui.take

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.miso.uxui.ialarmed.MainActivity
import com.miso.uxui.ialarmed.R
import com.miso.uxui.ialarmed.databinding.FragmentTakeSaveBinding
import com.miso.uxui.ialarmed.databinding.FragmentTakeStartBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TakeStartFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TakeStartFragment : Fragment() {

    private var _binding: FragmentTakeStartBinding? = null
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
        // Inflate the layout for this fragment

        _binding = FragmentTakeStartBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.menuButtonNo.setOnClickListener {
            // Crear un Intent para iniciar MainActivity
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }

        binding.menuButtonYes.setOnClickListener {
            // Crear un Intent para iniciar MainActivity
            findNavController().navigate(R.id.action_takeStartFragment_to_nav_take)
        }
    }

}
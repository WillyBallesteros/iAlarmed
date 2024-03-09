package com.miso.uxui.ialarmed.ui.take

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCapture.OutputFileResults
import androidx.camera.core.ImageCaptureException
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.core.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.miso.uxui.ialarmed.R
import com.miso.uxui.ialarmed.databinding.FragmentTakeCameraBinding
import java.io.File
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.Locale

class TakeFragment : Fragment() {

    private var _binding: FragmentTakeCameraBinding? = null
    private val binding get() = _binding!!
    private var imageCapture:ImageCapture?=null
    private lateinit var outputDirectory:File

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val takeViewModel = ViewModelProvider(this).get(TakeViewModel::class.java)
        _binding = FragmentTakeCameraBinding.inflate(inflater, container, false)
        val root: View = binding.root
        outputDirectory = outputDirectory()
        if( allPermissionGranted() ){
            startCamera()
        }else{
            ActivityCompat.requestPermissions(requireActivity(), Constants.REQUIRED_PERMISSIONS, Constants.REQUEST_CODE_PERMISSIONS)
        }
        binding.btnTakePhoto.setOnClickListener{ takePhoto() }
        return root
    }

    private fun outputDirectory():File{
        val mediaDir = requireActivity().externalMediaDirs.firstOrNull()?.let{ mFile->
            File(mFile, resources.getString(R.string.app_name) ).apply{
                mkdirs()
            }
        }
        return mediaDir ?: requireActivity().filesDir
    }

    private fun takePhoto(){
        val imageCapture = imageCapture?:return
        val photoFile = File(
            outputDirectory,
            SimpleDateFormat(Constants.FILE_NAME_FORMAT,
                Locale.getDefault())
                .format(System
                    .currentTimeMillis()) + ".jpg")
        val outputOption = ImageCapture
            .OutputFileOptions
            .Builder(photoFile)
            .build()

        imageCapture.takePicture(
            outputOption, ContextCompat.getMainExecutor(requireContext()),
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: OutputFileResults) {
                    findNavController().navigate(R.id.action_nav_take_to_nav_take_confirm)
                }
                override fun onError(exception: ImageCaptureException) {
                    Log.d(Constants.TAG, "onError: ${exception.message} ", exception)
                }
            }
        )
    }

    private fun allPermissionGranted() =
        Constants.REQUIRED_PERMISSIONS.all {
            ContextCompat.checkSelfPermission(
                requireContext(), it
            ) == PackageManager.PERMISSION_GRANTED
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder()
                .build()
                .also { mPreview ->
                    mPreview.setSurfaceProvider(
                        binding.viewFinder.surfaceProvider
                    )
                }
            imageCapture = ImageCapture.Builder()
                .build()
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
            try{
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, imageCapture
                )
            }catch (e:Exception){
                Log.d(Constants.TAG, "Inicio de camara fallo:",e)
            }
        }, ContextCompat.getMainExecutor(requireContext()))
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if ( requestCode == Constants.REQUEST_CODE_PERMISSIONS ){
            if (allPermissionGranted()){
                startCamera()
            }else{
                Toast.makeText(requireActivity(), "NOOOOO Permisos de camara", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
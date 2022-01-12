package com.example.mvvmretrofitrecylerviewpagingpager.fragment

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.mvvmretrofitrecylerviewpagingpager.R
import com.example.mvvmretrofitrecylerviewpagingpager.databinding.FragmentBiometricAuthBinding
import com.example.mvvmretrofitrecylerviewpagingpager.viewmodel.BiometricAuthViewModel


class BiometricAuthFragment : Fragment() {
    private lateinit var binding: FragmentBiometricAuthBinding
    private lateinit var viewModel: BiometricAuthViewModel

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel: BiometricAuthViewModel by viewModels()
        this.viewModel = tempViewModel

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_biometric_auth, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.checkBiometricSupport(requireActivity(), requireContext())
        binding.authButton.setOnClickListener {
            viewModel.authButtonClicked(requireActivity(), requireContext())
        }
    }


}
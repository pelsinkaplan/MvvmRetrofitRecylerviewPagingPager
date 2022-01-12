package com.example.mvvmretrofitrecylerviewpagingpager.viewmodel

import android.app.Activity
import android.app.Application
import android.app.KeyguardManager
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import android.os.CancellationSignal
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat

class BiometricAuthViewModel(application: Application) : BaseViewModel(application) {
    private var cancellationSignal: CancellationSignal? = null
    private val authenticationCallback: BiometricPrompt.AuthenticationCallback
        @RequiresApi(Build.VERSION_CODES.P)
        get() =
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
                    super.onAuthenticationError(errorCode, errString)
                    notifyUser("TouchID Sorunu: $errString")
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
                    super.onAuthenticationSucceeded(result)
                    notifyUser("TouchID Başarılı!")
                }
            }

    fun notifyUser(message: String) {
        Toast.makeText(getApplication(), message, Toast.LENGTH_SHORT).show()
    }

    private fun getCancellationSignal(): CancellationSignal {
        cancellationSignal = CancellationSignal()
        cancellationSignal?.setOnCancelListener {
            notifyUser("TouchID reddedildi.")
        }
        return cancellationSignal as CancellationSignal
    }

    fun checkBiometricSupport(requireActivity: Activity, requireContext: Context): Boolean {
        val keyguardManager: KeyguardManager =
            requireActivity.getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager

        if (!keyguardManager.isKeyguardSecure) {
            notifyUser("TouchID aktif değil, ayarlara gidin.")
            return false
        }
        if (ActivityCompat.checkSelfPermission(
                requireContext,
                android.Manifest.permission.USE_BIOMETRIC
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            notifyUser("TouchID aktif değil.")
            return false
        }

        return if (requireActivity.packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT))
            true
        else true
    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun authButtonClicked(requireActivity: Activity, requireContext: Context) {
        val biometricPrompt: BiometricPrompt = BiometricPrompt.Builder(requireContext)
            .setTitle("Title")
            .setSubtitle("Subtitle")
            .setDescription("This app created to implement touchID")
            .setNegativeButton("Cancel", requireActivity.mainExecutor,
                DialogInterface.OnClickListener { dialog, which ->
                    notifyUser("Cancelled")
                }).build()

        biometricPrompt.authenticate(
            getCancellationSignal(),
            requireActivity.mainExecutor, authenticationCallback
        )
    }
}
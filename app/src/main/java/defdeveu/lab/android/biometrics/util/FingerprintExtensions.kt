package defdeveu.lab.android.biometrics.util

import android.content.Context
import android.hardware.fingerprint.FingerprintManager
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat


object FingerprintExtensions {
    fun AppCompatActivity.startAuth(success: () -> Unit, failed: (message: String) -> Unit){
        val executor = ContextCompat.getMainExecutor(this)
        val biometricPrompt = BiometricPrompt(this, executor,
                object : BiometricPrompt.AuthenticationCallback() {
                    override fun onAuthenticationError(errorCode: Int,
                                                       errString: CharSequence) {
                        super.onAuthenticationError(errorCode, errString)
                        failed.invoke(errString.toString())
                    }

                    override fun onAuthenticationSucceeded(
                            result: BiometricPrompt.AuthenticationResult) {
                        super.onAuthenticationSucceeded(result)
                        success.invoke()
                    }

                    override fun onAuthenticationFailed() {
                        super.onAuthenticationFailed()
                        failed.invoke("Authentication failed")
                    }
                })

        biometricPrompt.authenticate(
                BiometricPrompt.PromptInfo.Builder()
                        .setTitle("Biometric login")
                        .setSubtitle("Log in using your biometric credential")
                        .setNegativeButtonText("Use account password")
                        .build()
        )
    }

    fun AppCompatActivity.canAuthenticateWithFingerprint(): Boolean{
        val fingerprintManager = getSystemService(Context.FINGERPRINT_SERVICE) as FingerprintManager

        return fingerprintManager.isHardwareDetected
    }
}
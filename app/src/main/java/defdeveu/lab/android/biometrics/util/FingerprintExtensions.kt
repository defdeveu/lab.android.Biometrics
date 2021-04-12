package defdeveu.lab.android.biometrics.util

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.fingerprint.FingerprintManager
import androidx.appcompat.app.AppCompatActivity

object FingerprintExtensions {
    fun AppCompatActivity.startAuth(success: () -> Unit, failed: (message: String) -> Unit) {
        val fingerprintManager = getSystemService(Context.FINGERPRINT_SERVICE) as FingerprintManager
        if (checkSelfPermission(Manifest.permission.USE_FINGERPRINT) == PackageManager.PERMISSION_GRANTED) {
            fingerprintManager.authenticate(null, null, 0,
                    object : FingerprintManager.AuthenticationCallback() {
                        override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
                            super.onAuthenticationError(errorCode, errString)
                            failed.invoke("onAuthenticationError: $errString")
                        }

                        override fun onAuthenticationFailed() {
                            super.onAuthenticationFailed()
                            failed.invoke("Authentication failed")
                        }

                        override fun onAuthenticationHelp(helpCode: Int, helpString: CharSequence?) {
                            super.onAuthenticationHelp(helpCode, helpString)
                            failed.invoke("onAuthenticationHelp: $helpString")
                        }

                        override fun onAuthenticationSucceeded(result: FingerprintManager.AuthenticationResult?) {
                            super.onAuthenticationSucceeded(result)
                            success.invoke()
                        }
                    },
                    null)
        }
    }

    fun AppCompatActivity.canAuthenticateWithFingerprint(): Boolean {
        val fingerprintManager = getSystemService(Context.FINGERPRINT_SERVICE) as FingerprintManager

        return fingerprintManager.isHardwareDetected
    }
}
package defdeveu.lab.android.biometrics.util

import android.content.Context
import android.hardware.fingerprint.FingerprintManager
import androidx.appcompat.app.AppCompatActivity


object FingerprintExtensions {
    fun AppCompatActivity.startAuth(){

    }

    fun AppCompatActivity.canAuthenticateWithFingerprint(): Boolean{
        val fingerprintManager = getSystemService(Context.FINGERPRINT_SERVICE) as FingerprintManager

        return fingerprintManager.isHardwareDetected
    }
}
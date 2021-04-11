package defdeveu.lab.android.biometrics

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import defdeveu.lab.android.biometrics.util.FingerprintExtensions.canAuthenticateWithFingerprint
import kotlinx.android.synthetic.main.activity_biometric.*

class BiometricActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_biometric)

        button.setOnClickListener {
            startActivity(Intent(this, SecretActivity::class.java))
            finish()
        }

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    override fun onResume() {
        super.onResume()
        button.isEnabled = canAuthenticateWithFingerprint()
    }
}
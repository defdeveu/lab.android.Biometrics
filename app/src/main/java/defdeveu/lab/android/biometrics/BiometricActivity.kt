package defdeveu.lab.android.biometrics

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import defdeveu.lab.android.biometrics.util.FingerprintExtensions.startAuth
import kotlinx.android.synthetic.main.activity_biometric.*

class BiometricActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_biometric)

        button.setOnClickListener {
            startAuth(success = {
                startActivity(Intent(this, SecretActivity::class.java))
                finish()
            }, failed = {
                message ->  Toast.makeText(this, message, Toast.LENGTH_LONG).show()
            })
        }

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}
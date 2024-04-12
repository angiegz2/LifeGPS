package com.clasecm1.lifegps

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashScreenActivity : AppCompatActivity() {

    private val splashTimeOut: Long = 2000 // 2 segundos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            // Se ejecutará después del tiempo especificado
            val intent = Intent(this, IniciarSesion::class.java)
            startActivity(intent)
            finish()
        }, splashTimeOut)
    }
}

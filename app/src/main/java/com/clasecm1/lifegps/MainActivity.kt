package com.clasecm1.lifegps

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity

class SplashScreenActivity : AppCompatActivity() {

    private val splashTimeOut: Long = 2000 // 2 segundos
    private lateinit var countDownTimer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // Iniciar el contador regresivo
        countDownTimer = object : CountDownTimer(splashTimeOut, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                // No necesitas hacer nada en cada tick, ya que solo esperamos el tiempo especificado
            }

            override fun onFinish() {
                // Se ejecutará después del tiempo especificado
                val intent = Intent(this@SplashScreenActivity, IniciarSesion::class.java)
                startActivity(intent)
                finish()
            }
        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        // Asegúrate de detener el contador regresivo para evitar fugas de memoria
        countDownTimer.cancel()
    }
}


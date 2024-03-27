package com.clasecm1.lifegps


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val handler = Handler(Looper.getMainLooper())

        handler.postDelayed({
            // Redirigir a IniciarSesion.kt después de 2 segundos (2000 milisegundos)
            val intent = Intent(this, IniciarSesion::class.java)
            startActivity(intent)
            finish() // Finaliza MainActivity para que no vuelva atrás
        }, 2000) // Retraso de 2 segundos (2000 milisegundos)


    }
}


package com.clasecm1.lifegps


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler().postDelayed({
            // Redirigir a IniciarSesion.kt después de 2 segundos (2000 milisegundos)
            val intent = Intent(this, IniciarSesion::class.java)
            startActivity(intent)
            finish() // Finalizar MainActivity para que no vuelva atrás
        }, 2000) // Retraso de 2 segundos (2000 milisegundos)
    }
}


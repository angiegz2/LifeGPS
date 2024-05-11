package com.clasecm1.lifegps

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_formulario_emergencia.btnActivarRastreo

class FormularioEmergencia : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_emergencia)

        btnActivarRastreo.setOnClickListener {
            // Aquí puedes activar el rastreo o iniciar algún tipo de seguimiento
        }
    }
}



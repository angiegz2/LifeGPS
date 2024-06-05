package com.clasecm1.lifegps

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.clasecm1.lifegps.databinding.ActivityFormularioEmergenciaBinding

class FormularioEmergencia : AppCompatActivity() {

    private lateinit var binding: ActivityFormularioEmergenciaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormularioEmergenciaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnActivarRastreo.setOnClickListener {
            val intent = Intent(this, ListaEmergenciasActivity::class.java)
            startActivity(intent)
        }
    }
}



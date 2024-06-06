package com.clasecm1.lifegps

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.clasecm1.lifegps.databinding.ActivityUsuarioRegistradoBinding

class UsuarioRegistrado : AppCompatActivity() {

    private lateinit var binding: ActivityUsuarioRegistradoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsuarioRegistradoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnActivarRastreo.setOnClickListener {
            val intent = Intent(this, ListaEmergenciasActivity::class.java)
            startActivity(intent)
        }
    }
}
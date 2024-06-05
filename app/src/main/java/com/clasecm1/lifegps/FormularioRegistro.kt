package com.clasecm1.lifegps

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.clasecm1.lifegps.databinding.ActivityRegistroBinding

class RegistroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val btnRegistrarse = binding.btnRegistrarse
        val etNombre = binding.etNombreCompleto

        binding.btnRegistrarse.setOnClickListener {
            val intent = Intent(this, ListaEmergenciasActivity::class.java)
            startActivity(intent)
        }
    }
}

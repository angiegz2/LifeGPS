package com.clasecm1.lifegps

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.clasecm1.lifegps.databinding.ActivityAtencionUsuarioBinding

class AtencionUsuario : AppCompatActivity() {
    private lateinit var binding: ActivityAtencionUsuarioBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAtencionUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAtencion.setOnClickListener {
            val intent = Intent(this, FormularioEmergencia::class.java)
            startActivity(intent)
        }
    }
}

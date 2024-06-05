package com.clasecm1.lifegps

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.clasecm1.lifegps.databinding.ActivitySeleccionEmergenciaBinding

data class Emergencia(
    val nombre: String,
    val color: Int
)


class SeleccionEmergenciaActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySeleccionEmergenciaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeleccionEmergenciaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnBuscar.setOnClickListener {
            val intent = Intent(this, ListaEmergenciasActivity::class.java)
            startActivity(intent)
        }
    }
}

package com.clasecm1.lifegps

import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ListaEmergenciasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_emergencias)

        val lvEmergencias = findViewById<ListView>(R.id.lvEmergencias)

        val emergencias = listOf(
            Emergencia("Infarto", R.color.colorRojo),
            Emergencia("Fractura", R.color.colorNaranja),
            Emergencia("Desmayo", R.color.colorAzul),
            Emergencia("Corte leve", R.color.colorVerde)
        )

        val adapter = EmergenciaAdapter(this, emergencias)
        lvEmergencias.adapter = adapter

        lvEmergencias.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val selectedEmergencia = emergencias[position]
            Toast.makeText(this, "Seleccionaste: ${selectedEmergencia.nombre}", Toast.LENGTH_SHORT).show()
        }
    }
}

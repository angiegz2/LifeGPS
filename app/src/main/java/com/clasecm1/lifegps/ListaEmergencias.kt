package com.clasecm1.lifegps

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class ListaEmergenciasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_emergencias)

        val lvEmergencias = findViewById<ListView>(R.id.lvEmergencias)

        val emergencias = listOf(
            Emergencia("Infarto", R.color.colorRojo, "Alerta extrema"),
            Emergencia("Fractura", R.color.colorNaranja, "Urgente"),
            Emergencia("Desmayo", R.color.colorAzul, "Grave"),
            Emergencia("Corte leve", R.color.colorVerde, "Leve")
        )

        val adapter = EmergenciaAdapter(this, emergencias)
        lvEmergencias.adapter = adapter
    }
}
package com.clasecm1.lifegps

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import com.clasecm1.lifegps.Emergencia

class EmergenciaAdapter(private val context: Context, private val emergencias: List<Emergencia>) : BaseAdapter() {

    override fun getCount(): Int {
        return emergencias.size
    }

    override fun getItem(position: Int): Any {
        return emergencias[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView = convertView ?: inflater.inflate(R.layout.item_emergencia, parent, false)

        val tvNombre = rowView.findViewById<TextView>(R.id.tvNombre)
        val emergencia = getItem(position) as Emergencia

        tvNombre.text = emergencia.nombre
        rowView.setBackgroundColor(emergencia.color)

        // Añadir OnClickListener al ítem de la lista
        rowView.setOnClickListener {
            // Manejar el clic en el ítem
            onEmergenciaItemClick(emergencia)
        }

        return rowView
    }

    private fun onEmergenciaItemClick(emergencia: Emergencia) {
        // Aquí puedes definir la lógica cuando se hace clic en una emergencia
        // Puedes mostrar un mensaje, abrir una nueva actividad, etc.
        // Por ejemplo:
        Toast.makeText(context, "Seleccionaste: ${emergencia.nombre}", Toast.LENGTH_SHORT).show()
    }
}

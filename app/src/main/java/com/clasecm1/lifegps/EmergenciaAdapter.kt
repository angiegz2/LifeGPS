package com.clasecm1.lifegps

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast

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
        val rowView = convertView ?: inflater.inflate(R.layout.list_item, parent, false)

        val tvNombre = rowView.findViewById<TextView>(R.id.tvNombre)
        val tvGravedad = rowView.findViewById<TextView>(R.id.tvGravedad)
        val emergencia = getItem(position) as Emergencia

        tvNombre.text = emergencia.nombre
        tvGravedad.text = emergencia.gravedad
        rowView.setBackgroundColor(emergencia.color)


        rowView.setOnClickListener {
            val emergencia = getItem(position) as Emergencia
            onEmergenciaItemClick(emergencia)
        }

        return rowView
    }

    private fun onEmergenciaItemClick(emergencia: Emergencia) {
        Toast.makeText(context, "Seleccionaste: ${emergencia.nombre}", Toast.LENGTH_SHORT).show()
    }
}

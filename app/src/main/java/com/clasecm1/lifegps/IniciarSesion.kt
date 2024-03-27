package com.clasecm1.lifegps


import android.Manifest
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.util.*

class IniciarSesion : AppCompatActivity() {

    private lateinit var textHoraFechaUbicacion: TextView
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iniciar_sesion)

        textHoraFechaUbicacion = findViewById(R.id.textHoraFechaUbicacion)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        if (checkLocationPermission()) {
            obtenerUbicacionActual()
        } else {
            solicitarPermisos()
        }
    }

    private fun checkLocationPermission(): Boolean {
        val fineLocationPermission = ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val coarseLocationPermission = ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        return fineLocationPermission && coarseLocationPermission
    }


    private fun solicitarPermisos() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            REQUEST_LOCATION_PERMISSION
        )
    }


    private fun obtenerUbicacionActual() {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {
                    val geocoder = Geocoder(this, Locale.getDefault())
                    val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)

                    if (addresses.isNotEmpty()) {
                        val ciudad = addresses[0].locality ?: "Desconocido"
                        val pais = addresses[0].countryName ?: "Desconocido"

                        val cal = Calendar.getInstance()
                        val hora = cal.get(Calendar.HOUR_OF_DAY)
                        val minutos = cal.get(Calendar.MINUTE)
                        val fecha = "${cal.get(Calendar.DAY_OF_MONTH)}/${cal.get(Calendar.MONTH) + 1}/${cal.get(Calendar.YEAR)}"

                        val ubicacionTexto = "Hora: $hora:$minutos | Fecha: $fecha | Ubicación: $ciudad, $pais"
                        textHoraFechaUbicacion.text = ubicacionTexto
                    }
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error al obtener la ubicación: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    companion object {
        private const val REQUEST_LOCATION_PERMISSION = 1001
    }
}

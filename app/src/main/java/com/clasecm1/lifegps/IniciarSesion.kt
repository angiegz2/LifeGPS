package com.clasecm1.lifegps

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import com.clasecm1.lifegps.databinding.ActivityIniciarSesionBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Locale

class IniciarSesion : AppCompatActivity() {

    private lateinit var textHora: TextView
    private lateinit var textFecha: TextView
    private lateinit var textUbicacion: TextView
    private lateinit var editTextCorreoNumero: EditText
    private lateinit var editTextContrasena: EditText
    private lateinit var btnIniciarSesion: Button
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var binding: ActivityIniciarSesionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIniciarSesionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        textHora = binding.textHora
        textFecha = binding.textFecha
        textUbicacion = binding.textUbicacion
        editTextCorreoNumero = binding.editTextCorreoNumero
        editTextContrasena = binding.editTextContrasena
        btnIniciarSesion = binding.btnIniciarSesion

        lifecycleScope.launch(Dispatchers.IO){}
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        binding.btnIniciarSesion.setOnClickListener {
            val correoONumero = editTextCorreoNumero.text.toString()
            val contrasena = editTextContrasena.text.toString()

            if (inicioDeSesionExitoso(correoONumero, contrasena)) {
                val intent = Intent(this, AtencionUsuario::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Inicio de sesión fallido", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnRegistrarse.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }

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
        if (checkLocationPermission() && isNetworkConnected()) {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location ->
                    location?.let {
                        lifecycleScope.launch(Dispatchers.IO) {
                            val geocoder = Geocoder(this@IniciarSesion, Locale.getDefault())
                            try {
                                val addresses = geocoder.getFromLocation(it.latitude, it.longitude, 1)
                                launch(Dispatchers.Main) {
                                    if (!addresses.isNullOrEmpty()) {
                                        val address = addresses[0]
                                        val ciudad = address.locality ?: "Desconocido"
                                        val pais = address.countryName ?: "Desconocido"

                                        val cal = Calendar.getInstance()

                                        val horaTexto = "Hora: ${cal.get(Calendar.HOUR_OF_DAY)}:${cal.get(Calendar.MINUTE)}"
                                        textHora.text = horaTexto

                                        val fechaTexto = "Fecha: ${cal.get(Calendar.DAY_OF_MONTH)}/${cal.get(Calendar.MONTH) + 1}/${cal.get(Calendar.YEAR)}"
                                        textFecha.text = fechaTexto

                                        val ubicacionTexto = "Ubicación: $ciudad, $pais"
                                        textUbicacion.text = ubicacionTexto
                                    } else {
                                        Toast.makeText(this@IniciarSesion, "No se encontraron direcciones", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            } catch (e: Exception) {
                                launch(Dispatchers.Main) {
                                    Toast.makeText(this@IniciarSesion, "Error al obtener la ubicación: ${e.message}", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    } ?: run {
                        Toast.makeText(this, "No se pudo obtener la ubicación", Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error al obtener la ubicación: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        } else {
            if (!checkLocationPermission()) {
                solicitarPermisos()
            } else {
                Toast.makeText(this, "Se requiere conexión a internet", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun isNetworkConnected(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

        return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    private fun inicioDeSesionExitoso(correoOnumero: String, contrasena: String): Boolean {
        return true
    }

    companion object {
        private const val REQUEST_LOCATION_PERMISSION = 1001
    }
}

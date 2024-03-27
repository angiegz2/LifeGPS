package com.clasecm1.lifegps


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.clasecm1.lifegps.IniciarSesion

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Aquí puedes agregar algún código para verificar si el usuario ya inició sesión
        // Si el usuario ya inició sesión, puedes redirigirlo directamente a otra actividad

        // Ejemplo de redireccionamiento a IniciarSesion.kt
        val intent = Intent(this, IniciarSesion::class.java)
        startActivity(intent)
        finish() // Opcional: finaliza MainActivity para que no vuelva atrás presionando el botón de retroceso
    }
}

package com.cbellmont.ejemploandroid8

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.cbellmont.ejemploandroid8.databinding.ActivityMainBinding
import java.lang.Exception
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "Hola"
    }

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cargarPreferencias()?.let {
            binding.editText.setText(it.nombre)
        }
    }

    override fun onStop() {
        val persona = Persona(binding.editText.toString(), Random.nextInt(0,99))
        guardarPreferencias(persona)
        super.onStop()
    }

    private fun cargarPreferencias() : Persona? {
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        sharedPref.getString(TAG, "")?.let {
            return try {
                Log.d(MainActivity::class.java.name, "La persona encontrada es $it")
                Persona.fromJson(it)
            } catch (e : Exception) {
                Log.w(MainActivity::class.java.name, "No se han podido cargar correctamente las preferencias.")
                Log.w(MainActivity::class.java.name, "El valor encontrado es : $it")
                null
            }
        }
        return null
    }

    private fun guardarPreferencias(persona: Persona) {
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        /* Lo de abajo es una manera más elegante de poner esto mismo.
        var sharedPrefEditable = sharedPref.edit()
        sharedPrefEditable.putString("NOMBRE", "valor guardado")
        sharedPrefEditable.commit() */

        with(sharedPref.edit()) {
            putString(TAG, persona.toJson())
            commit()
        }
    }

}
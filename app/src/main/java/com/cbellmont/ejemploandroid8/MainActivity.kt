package com.cbellmont.ejemploandroid8

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.cbellmont.ejemploandroid8.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "Hola"
    }

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cargarPreferencias()?.let { binding.editText.setText(it) }

        binding.editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                guardarPreferencias(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

    }


    private fun cargarPreferencias() : String? {
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        return sharedPref.getString(TAG, "")
    }

    private fun guardarPreferencias(string : String) {
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        /* Lo de abajo es una manera más elegante de poner esto mismo.
        var sharedPrefEditable = sharedPref.edit()
        sharedPrefEditable.putString("NOMBRE", "asdfasdf")
        sharedPrefEditable.commit() */

        with (sharedPref.edit()) {
            putString(TAG, string)
            commit()
        }
    }
}
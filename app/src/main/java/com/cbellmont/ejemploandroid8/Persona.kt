package com.cbellmont.ejemploandroid8

import com.google.gson.Gson
import kotlinx.serialization.Serializable

@Serializable
data class Persona(var nombre: String, var edad: Int) {

    companion object {

        fun fromJson(json : String) : Persona {
            val gson = Gson()
            return gson.fromJson(json, Persona::class.java)
        }
    }

    fun toJson() : String {
        val gson = Gson()
        return gson.toJson(this)
    }

}
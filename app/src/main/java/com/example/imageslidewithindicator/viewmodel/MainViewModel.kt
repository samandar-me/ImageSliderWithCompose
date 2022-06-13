package com.example.imageslidewithindicator.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imageslidewithindicator.model.Movie
import com.example.imageslidewithindicator.network.RetroInstance
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    var movieListResponse: List<Movie> by mutableStateOf(listOf())

    fun getAllMovies() {
        viewModelScope.launch {
            try {
                movieListResponse = RetroInstance.getRetroInstance().getAllMovies()
            } catch (e: Exception) {
                Log.d("TAG", e.stackTraceToString())
            }
        }
    }
}
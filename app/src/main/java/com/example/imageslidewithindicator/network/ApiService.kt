package com.example.imageslidewithindicator.network

import com.example.imageslidewithindicator.model.Movie
import retrofit2.http.GET

interface ApiService {
    @GET("movielist.json")
    suspend fun getAllMovies(): List<Movie>
}
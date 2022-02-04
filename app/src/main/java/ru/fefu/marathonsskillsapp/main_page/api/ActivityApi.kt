package ru.fefu.marathonsskillsapp.main_page.api

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ActivityApi {

    @POST("/api/registration")
    suspend fun register(
        @Query("login") login: String,
        @Query("password") password: String,
        @Query("surname") name: String,
        @Query("name") gender: String,
        @Query("email") email: String,
    ): Token

    @POST("/api/login")
    suspend fun login(
        @Query("login") login: String,
        @Query("password") password: String,
    ): Token

    @POST("/api/logout")
    suspend fun logout(): Unit

    @GET("/api/profile")
    suspend fun getProfile(): User
}
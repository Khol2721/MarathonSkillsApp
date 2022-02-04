package ru.fefu.marathonsskillsapp.main_page.api

data class User(
    val id: Int,
    val login: String,
    val name: String,
    val surname: String,
    val email: String,
)

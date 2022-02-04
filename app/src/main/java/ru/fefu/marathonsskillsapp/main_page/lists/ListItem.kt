package ru.fefu.marathonsskillsapp.main_page.lists

sealed class ListItem {
    data class Item(
        val distance: String,
        val time: String,
        val cost: String,
        val activity: String,
        val startTime: String,
        val endTime: String,
        val info: String,
        val user: String = "",
    ) : ListItem()

    data class Date(
        val date: String,
    ) : ListItem()
}




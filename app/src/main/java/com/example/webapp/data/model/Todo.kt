package com.example.webapp.data.model

data class Todo(
    val completed: Boolean,
    val id: Int,
    val title: String,
    var userId: Int
)
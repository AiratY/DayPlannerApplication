package com.example.dayplannerapplication.data

import java.sql.Timestamp

data class Task(
    val id: Int,
    val dateStart: Timestamp,
    val dateEnd: Timestamp,
    val name: String,
    val description: String
)

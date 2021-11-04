package com.example.dayplannerapplication.data

import java.sql.Timestamp

fun tasksList(): List<Task> {
    return listOf(
        Task(
            id = 1,
            dateStart = Timestamp(147600000),
            dateEnd = Timestamp(147600000),
            name = "Поход в магазин",
            description = "Купить: батон, молоко, чай"
        ),
        Task(
            id = 2,
            dateStart = Timestamp(147600000),
            dateEnd = Timestamp(147600000),
            name = "Занятие спортом",
            description = "Купить: батон, молоко, чай"
        ),
        Task(
            id = 3,
            dateStart = Timestamp(147600000),
            dateEnd = Timestamp(147600000),
            name = "Поход в магазин",
            description = "Купить: батон, молоко, чай"
        ),
        Task(
            id = 4,
            dateStart = Timestamp(147600000),
            dateEnd = Timestamp(147600000),
            name = "Поход в магазин",
            description = "Купить: батон, молоко, чай"
        ),
        Task(
            id = 5,
            dateStart = Timestamp(147600000),
            dateEnd = Timestamp(147600000),
            name = "Поход в магазин",
            description = "Купить: батон, молоко, чай"
        ),
        Task(
            id = 6,
            dateStart = Timestamp(147600000),
            dateEnd = Timestamp(147600000),
            name = "Поход в магазин",
            description = "Купить: батон, молоко, чай"
        ),

    )
}

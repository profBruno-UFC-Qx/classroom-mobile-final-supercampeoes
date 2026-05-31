package com.example.superchampions.model

import kotlinx.serialization.Serializable

@Serializable
data class Event(
    val id: Int,
    val title: String,
    val date: String,
    val location: String,
    val prize: String,
    val status: EventStatus,
    val imageUrl: String
)

enum class EventStatus {
    OPEN,
    CLOSED
}
package com.example.superchampions.model

import kotlinx.serialization.Serializable

@Serializable
data class Athlete (
    val id: Int,
    val name: String,
    val cord: String,
    val gym: String,
    val points: Int,
    val position: Int = 0
)
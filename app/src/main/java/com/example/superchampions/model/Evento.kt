package com.example.superchampions.model

import kotlinx.serialization.Serializable

@Serializable
data class Evento(
    val id: String,
    val titulo: String,
    val data: String,
    val local: String,
    val premiacao: String
)
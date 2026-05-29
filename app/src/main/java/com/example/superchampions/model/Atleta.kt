package com.example.superchampions.model

import kotlinx.serialization.Serializable

@Serializable
data class Atleta (
    val id: String,
    val nome: String,
    val corda: String,
    val academia: String,
    val pontos: Int,
    val posicao: Int = 0
)
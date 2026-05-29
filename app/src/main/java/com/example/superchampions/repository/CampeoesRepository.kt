package com.example.superchampions.repository

import com.example.superchampions.model.Atleta
import com.example.superchampions.model.Evento

class CampeoesRepository {
    fun getRanking(): List<Atleta> = listOf(
        Atleta("1", "Ricardo 'Mestre' Lima", "Mestre", "Centro Cultural Senzala", 3120, 1),
        Atleta("2", "Carla Santos", "Professora", "Abadá", 2840, 2),
        Atleta("3", "André Luiz", "Instrutor", "Cordão de Ouro", 2510, 3)
    )

    fun getEventosProximos(): List<Evento> = listOf(
        Evento("1", "Torneio de Ouro", "15 Out, 2024", "Salvador, BA", "R$ 5.000"),
        Evento("2", "Copa Regional", "12 Nov, 2024", "Rio de Janeiro, RJ", "R$ 3.000")
    )
}
package com.example.superchampions.data

import com.example.superchampions.model.Atleta
import com.example.superchampions.model.Evento
import com.example.superchampions.model.Perfil

object FakeData {
    val atletas = listOf(
        Atleta("1", "Guilherme Warley", "Mestre", "Centro Cultural de Quixadá", 3120, 1),
        Atleta("2", "Bruno Gois", "Professor", "Ginga", 2840, 2),
        Atleta("3", "Tiago Feitoza", "Instrutor", "Cordão de Ouro", 2510, 3)
    )

    val eventos = listOf(
        Evento("1", "Torneio de Ouro", "31 Mai, 2026", "Quixadá, CE", "R$ 5.000"),
        Evento("2", "Copa Regional", "4 Jun, 2026", "Fortaleza, CE", "R$ 3.000"),
        Evento("3", "Desafio dos Mestres", "20 Dez, 2026", "São Paulo, SP", "R$ 10.000")
    )

    val perfil = Perfil(
        nome = "Guilherme Warley",
        academia = "Centro Cultural de Quixadá",
        corda = "Mestre",
        pontos = 3120
    )
}

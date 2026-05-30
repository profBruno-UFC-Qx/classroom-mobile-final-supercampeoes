package com.example.superchampions.repository

import com.example.superchampions.data.FakeData
import com.example.superchampions.model.Atleta
import com.example.superchampions.model.Evento
import com.example.superchampions.model.Perfil

class CampeoesRepository {

    suspend fun getRanking(): List<Atleta> = FakeData.atletas

    suspend fun getEventosProximos(): List<Evento> = FakeData.eventos

    suspend fun getPerfil(): Perfil = FakeData.perfil
}
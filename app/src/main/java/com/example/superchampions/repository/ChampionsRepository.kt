package com.example.superchampions.repository

import com.example.superchampions.data.FakeData
import com.example.superchampions.model.Athlete
import com.example.superchampions.model.Event
import com.example.superchampions.model.Profile

class ChampionsRepository {

    suspend fun getRanking(): List<Athlete> = FakeData.athletes

    suspend fun getEvents(): List<Event> = FakeData.events

    suspend fun getProfile(): Profile = FakeData.profile
}
package com.example.superchampions.data

import com.example.superchampions.model.Athlete
import com.example.superchampions.model.Event
import com.example.superchampions.model.Profile
import com.example.superchampions.model.EventStatus
object FakeData {
    val athletes = listOf(
        Athlete(1, "Guilherme Warley", "Vermelha", "Centro Cultural de Quixadá", 3120, 1),
        Athlete(2, "Bruno Gois", "Amarela", "Capoeira Monólitos", 2840, 2),
        Athlete(3, "Tiago Feitoza", "Roxa", "Sertão Capoeira", 2510, 3),
        Athlete(4, "Marcelo Menezes", "Azul", "UFC Capoeira", 2510, 4)
    )

    val events = listOf(
        Event(
            id = 1, 
            title = "Copa Sertão de Capoeira", 
            date = "15 Set, 2026", 
            location = "Ginásio Rinaldo Roger", 
            prize = "R$ 5.000",
            status = EventStatus.OPEN,
            imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSr4fLsu0HKAoaHRtLxIW0w39nhVxBgnq7wLw&s"
        ),
        Event(
            id = 2,
            title = "Encontro dos Monólitos", 
            date = "02 Out, 2026", 
            location = "Praça do Leão", 
            prize = "Certificado",
            status = EventStatus.OPEN,
            imageUrl = "https://www.google.com/url?sa=t&source=web&rct=j&url=https://www.aviva.com.br/blog/capoeira-simbolo-de-resistencia-e-sua-importancia-na-bahia&ved=2ahUKEwjO5NSD2OGUAxXDBLkGHcysDE4QjRx6BAgEEBY&opi=89978449&usg=AOvVaw370CjfUth2VQx8eKRUCts4"
        ),
        Event(
            id = 3, 
            title = "Batizado e Troca de Cordas", 
            date = "20 Dez, 2026", 
            location = "UFC Campus Quixadá", 
            prize = "R$ 10.000",
            status = EventStatus.OPEN,
            imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSN0Xv6e7-Znbog6xmIKdqNe9v4xmlWzUnNBg&s"
        ),
        Event(
            id = 4, 
            title = "Roda na Galinha Choca", 
            date = "10 Jul, 2025", 
            location = "Açude do Cedro", 
            prize = "Medalhas",
            status = EventStatus.CLOSED,
            imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS4ZqAxvjXjxh7kdgGtxHiYXGxzRE3oI1nf8w&s"
        )
    )

    val profile = Profile(
        name = "Guilherme Warley",
        gym = "Centro Cultural de Quixadá",
        cord = "Vermelha",
        points = 3120
    )
}

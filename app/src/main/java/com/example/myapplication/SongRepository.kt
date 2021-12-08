package com.example.myapplication

object SongRepository {
    val songsList: List<Song> = arrayListOf(
        Song(1, R.drawable.rassvet, R.raw.kish_rassvet, "Утренний рассвет", "Король и шут", "Добро"),
        Song(2, R.drawable.motocikl, R.raw.kish_motocicl, "Мотоцикл", "Король и шут", "Не добро"),
        Song(3, R.drawable.cat, R.raw.cat, "Кот", "Ручной Рептилоид", "Не добро"),
        Song(4, R.drawable.maxresdefault, R.raw.cover_white_night, "Белая ночь", "umilele", "Добро"),
        Song(5, R.drawable.frenchg, R.raw.french, "Les Champs-Elysées", "Pomplamoose ft. John Schroeder", "Добро"),
        Song(6, R.drawable.maneskin, R.raw.maneskin_zitti_e_buonni, "ZITTI E BUONNI", "Maneskin", "Добро"),
        Song(7, R.drawable.lamericano, R.raw.lamericano, "Tu Vuo' Fa' L'Americano", "Hetty & the Jazzato Band", "Добро"),
    )
}
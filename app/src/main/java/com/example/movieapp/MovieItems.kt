package com.example.movieapp

import android.os.Parcelable
import android.util.Log
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import kotlin.random.Random

/**
 * Класс с данными фильмов
 */
@Parcelize
class MovieItems(val id: Int, val imgResId: Int, val textResId: Int, var colorResId: Int) : Parcelable {
    companion object {
        private var randomMovies: MutableList<MovieItems> = mutableListOf()

        private val movies = mutableListOf(
            MovieItems(1, R.drawable.widow, R.string.widowText, R.color.white),
            MovieItems(2, R.drawable.luka, R.string.lukaText, R.color.white),
            MovieItems(3, R.drawable.spirit, R.string.spiritText, R.color.white)
        )

        /**
         * Вспомогательный метод для получения списка случайных фильмов
         */
        fun getRandomMovies(howMany: Int): MutableList<MovieItems> {
            return if (randomMovies.isNullOrEmpty()) {
                for (i in 1..howMany) randomMovies.add(movies[Random.nextInt(0, 3)])
                randomMovies
            } else randomMovies
        }
    }
}


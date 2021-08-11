package com.example.movieapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class MovieItem(val imgResId: Int, val textResId: Int, var colorResId: Int) : Parcelable {
    companion object {
        val movies = mutableListOf(
            MovieItem(R.drawable.widow, R.string.widowText, R.color.white),
            MovieItem(R.drawable.luka, R.string.lukaText, R.color.white),
            MovieItem(R.drawable.spirit, R.string.spiritText, R.color.white),
            MovieItem(R.drawable.widow, R.string.widowText, R.color.white),
            MovieItem(R.drawable.luka, R.string.lukaText, R.color.white),
            MovieItem(R.drawable.spirit, R.string.spiritText, R.color.white),
            MovieItem(R.drawable.widow, R.string.widowText, R.color.white),
            MovieItem(R.drawable.luka, R.string.lukaText, R.color.white),
            MovieItem(R.drawable.spirit, R.string.spiritText, R.color.white),
            MovieItem(R.drawable.widow, R.string.widowText, R.color.white),
            MovieItem(R.drawable.luka, R.string.lukaText, R.color.white),
            MovieItem(R.drawable.spirit, R.string.spiritText, R.color.white),
            MovieItem(R.drawable.widow, R.string.widowText, R.color.white),
            MovieItem(R.drawable.luka, R.string.lukaText, R.color.white),
            MovieItem(R.drawable.spirit, R.string.spiritText, R.color.white)
        )
    }
}


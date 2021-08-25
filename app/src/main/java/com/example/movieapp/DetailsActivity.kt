package com.example.movieapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Экран детального описание фильма
 */
class DetailsActivity : AppCompatActivity() {
    /**
     * В методе определяется layout для Activity, исходя из элемента, по которму был выполнен клик
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val item: MovieItems? = intent.getParcelableExtra("movieItem")
        when (item?.let { item.id }) {
            1 -> setContentView(R.layout.activity_details_1)
            2 -> setContentView(R.layout.activity_details_2)
            3 -> setContentView(R.layout.activity_details_3)
        }
    }
}
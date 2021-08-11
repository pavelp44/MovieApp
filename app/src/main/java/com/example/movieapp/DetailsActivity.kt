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
        val item: MovieItem? = intent.getParcelableExtra("movieItem")
        when (item?.let { getText(it.textResId).toString() }) {
            "Черная вдова" -> setContentView(R.layout.activity_details_1)
            "Лука" -> setContentView(R.layout.activity_details_2)
            "Унесенные призраками" -> setContentView(R.layout.activity_details_3)
        }
    }
}
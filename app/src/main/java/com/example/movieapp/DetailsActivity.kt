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
        val viewId = intent.getIntExtra("view", 0)
        if (viewId != 0) {
            setContentView(viewId)
        }
    }
}
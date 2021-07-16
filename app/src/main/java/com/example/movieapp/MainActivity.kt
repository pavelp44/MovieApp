package com.example.movieapp

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.core.view.children

class MainActivity : AppCompatActivity() {
    companion object {
        var position = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<RelativeLayout>(R.id.layout).children.forEach {
            it.setBackgroundColor(resources.getColor(R.color.white)) // Устанавливаем фон на белый для всех элементов (debug)
        }
//
//            // Для сохранения состояния выделения фильма
//        savedInstanceState?.let {
//            when (it.getString("position")) {
//                "1" -> {
//                    val parent = findViewById<ImageButton>(R.id.button1).parent
//                    if (parent != null) {
//                        (parent as View).setBackgroundColor(resources.getColor(R.color.purple_200))
//                    }
//                }
//                "2" -> {
//                    val parent = findViewById<ImageButton>(R.id.button2).parent
//                    if (parent != null) {
//                        (parent as View).setBackgroundColor(resources.getColor(R.color.purple_200))
//                    }
//                }
//                "3" -> {
//                    val parent = findViewById<ImageButton>(R.id.button2).parent
//                    if (parent != null) {
//                        (parent as View).setBackgroundColor(resources.getColor(R.color.purple_200))
//                    }
//                }
//            }
//            it.clear()
//        }

        findViewById<ImageButton>(R.id.button1).setOnClickListener {
            val intent = Intent(this, DetailsActivity::class.java)
            val parent = it.parent
            if (parent != null) {
                (parent as View).setBackgroundColor(resources.getColor(R.color.purple_200))
            }
            position = "1"
            startActivity(intent)
        }
        findViewById<ImageButton>(R.id.button2).setOnClickListener {
            val intent = Intent(this, DetailsActivity::class.java)
            val parent = it.parent
            if (parent != null) {
                (parent as View).setBackgroundColor(resources.getColor(R.color.purple_200))
            }
            position = "2"
            startActivity(intent)
        }
        findViewById<ImageButton>(R.id.button3).setOnClickListener {
            val intent = Intent(this, DetailsActivity::class.java)
            val parent = it.parent
            if (parent != null) {
                (parent as View).setBackgroundColor(resources.getColor(R.color.purple_200))
            }
            position = "3"
            startActivity(intent)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("position", position)
    }
}
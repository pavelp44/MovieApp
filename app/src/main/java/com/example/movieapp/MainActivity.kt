package com.example.movieapp

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<ImageButton>(R.id.button1).setOnClickListener {
            var intent = Intent(this, DetailsActivity::class.java)
            var parent = it.parent
            if (parent != null) {
                (parent as View).backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.purple_200))
            }

            intent.putExtra("position", )
            startActivity(intent)
        }
    }
}
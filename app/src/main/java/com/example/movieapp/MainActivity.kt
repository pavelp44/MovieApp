package com.example.movieapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    companion object {
        var clickedElementId = 0
        var rowId = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        savedInstanceState?.let {
            if (clickedElementId != 0) setBackgroundColor(
                findViewById(savedInstanceState.getInt("clicked_elem_id")), // Установить выделение ранее выбранного элемента
                R.color.purple_200
            )
        }

        startActivityOnClick(
            listOf(R.id.button1, R.id.button2, R.id.button3),
            DetailsActivity::class.java
        )

        //Кнопка "Поделиться"
        findViewById<FloatingActionButton>(R.id.floatingActionButton).setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, "Вот тут есть ссылочка")
            startActivity(Intent.createChooser(intent, "Поделись с другом"))
        }
    }

    /**
     * Метод для запуска Activity по клику на один из списка элементов
     * Дополнительно элемент выделяется цветом
     * Дополнительно в зависимости от порядкого номера в intent записывается layout, который нужно отрисовать при открытии
     * @param elementList Список элементов, по клику на которые открывается Activity
     * @param activity Activity для открытия
     */
    private fun startActivityOnClick(
        elementList: List<Int>,
        activity: Class<out AppCompatActivity>
    ) {
        val intent = Intent(this, activity)
        elementList.forEach { el ->
            findViewById<View>(el).setOnClickListener {
                setBackgroundColor(it, R.color.purple_200)
                clickedElementId = el
                when (elementList.indexOf(el)) {
                    0 -> intent.putExtra("view", R.layout.activity_details_1)
                    1 -> intent.putExtra("view", R.layout.activity_details_2)
                    2 -> intent.putExtra("view", R.layout.activity_details_3)
                }
                startActivity(intent)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("clicked_elem_id", clickedElementId) //Для выделения цветом при пересоздании Activity
    }

    /**
     * Метод для выделения цветом элемента, по которому был сделать клик
     */
    private fun setBackgroundColor(element: View, color: Int) {
        val parent = element.parent
        if (parent != null) {
            findViewById<RelativeLayout>(R.id.layout).children.forEach {
                it.setBackgroundColor(resources.getColor(R.color.white)) // Чтобы убрать выделения, отставшиеся с предыдущих кликов
            }
            (parent as View).setBackgroundColor(resources.getColor(color))
        }
    }
}
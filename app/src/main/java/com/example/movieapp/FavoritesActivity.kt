package com.example.movieapp

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Экран избранного
 */
class FavoritesActivity : AppCompatActivity() {
    companion object {
        /**
         * Спиок избранных фильмов
         */
        var favouritesList = mutableListOf<MovieItem>()

        /**
         * Список фильмов, отмеченых для удаления
         */
        var itemsToDeleteList = mutableListOf<MovieItem>()

        /**
         * Активен ли режим удаления из избранного
         */
        private var isDeleteModeActive = false
    }

    private val recycler by lazy {
        findViewById<RecyclerView>(R.id.movieRecycler)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourites)
        initRecycler()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_favorites, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        isDeleteModeActive = isDeleteModeActive.not()
        recycler.adapter?.notifyItemRangeChanged(0, favouritesList.size)
        if (isDeleteModeActive) {
            findViewById<Button>(R.id.buttonDelete).let { button ->
                button.visibility = Button.VISIBLE
                button.setOnClickListener {
                    if (itemsToDeleteList.isNotEmpty()) {
                        itemsToDeleteList.forEach {
                            val position = favouritesList.indexOf(it)
                            favouritesList.removeAt(position)
                            recycler.adapter?.notifyItemRemoved(position)
                        }
                        itemsToDeleteList.clear()
                    }
                }
            }
        } else {
            findViewById<Button>(R.id.buttonDelete).visibility = Button.INVISIBLE
        }
        return super.onOptionsItemSelected(item)
    }

    class FavouritesVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var movieImg = itemView.findViewById<ImageView>(R.id.imageView)
        private var movieTitle = itemView.findViewById<TextView>(R.id.textView)

        fun bind(imgResId: Int, textResId: Int) {
            movieImg.setImageResource(imgResId)
            movieTitle.setText(textResId)
        }
    }

    class FavouritesAdapter(
        private val items: List<MovieItem>,
        private val action: (View, MovieItem, Int) -> Unit  // Функция для выполения действия над item view
    ) : RecyclerView.Adapter<FavouritesVH>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouritesVH {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie_favorites, parent, false)
            return FavouritesVH(view)
        }

        override fun onBindViewHolder(holder: FavouritesVH, position: Int) {
            val item = items[position]
            holder.bind(item.imgResId, item.textResId)
            holder.itemView.let { action.invoke(it, item, position) }
        }

        override fun getItemCount() = items.count()
    }

    private fun initRecycler() {
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = FavouritesAdapter(favouritesList) { view, item, position ->
            if (isDeleteModeActive) {
                val checkBox = view.findViewById<CheckBox>(R.id.checkbox)
                checkBox.visibility = CheckBox.VISIBLE
                if (!checkBox.hasOnClickListeners()) {
                    checkBox.setOnClickListener {
                        itemsToDeleteList.add(item)
                    }
                }
            }
        }
    }
}
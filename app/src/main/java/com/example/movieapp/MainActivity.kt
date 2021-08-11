package com.example.movieapp

import android.content.Intent
import android.content.res.Configuration.ORIENTATION_PORTRAIT
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R.color.teal_700
import com.example.movieapp.R.color.white

class MainActivity : AppCompatActivity() {
    private val recycler by lazy {
        findViewById<RecyclerView>(R.id.movieRecycler)
    }

    private val movies = MovieItem.movies

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecycler()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        startActivity(Intent(this, FavoritesActivity::class.java))
        return super.onOptionsItemSelected(item)
    }

    class MovieVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var movieImg = itemView.findViewById<ImageView>(R.id.imageView)
        private var movieTitle = itemView.findViewById<TextView>(R.id.textView)

        fun bind(imgResId: Int, textResId: Int, colorResId: Int) {
            movieImg.setImageResource(imgResId)
            movieTitle.setText(textResId)
            itemView.setBackgroundResource(colorResId)
        }
    }

    class MovieAdapter(
        private val items: List<MovieItem>,
        private val action: (View, MovieItem, Int) -> Unit
    ) : RecyclerView.Adapter<MovieVH>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieVH {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_movie_main, parent, false)
            return MovieVH(view)
        }

        override fun onBindViewHolder(holder: MovieVH, position: Int) {
            val item = items[position]
            holder.bind(item.imgResId, item.textResId, item.colorResId)
            holder.itemView.let { action.invoke(it, item, position) }
        }

        override fun getItemCount() = items.count()
    }

    private fun initRecycler() {
        if (resources.configuration.orientation == ORIENTATION_PORTRAIT) {
            recycler.layoutManager = LinearLayoutManager(this)
        } else {
            recycler.layoutManager = GridLayoutManager(this, 3)
        }
        recycler.adapter = MovieAdapter(movies) { view, item, position ->
            view.setOnClickListener {
                movies.forEach {
                    if (it.colorResId != white) {
                        it.colorResId = white
                        recycler.adapter?.notifyItemChanged(movies.indexOf(it))
                    }
                }

                movies[position] = MovieItem(
                    movies[position].imgResId,
                    movies[position].textResId,
                    teal_700
                )

                recycler.adapter?.notifyItemChanged(position)
                val intent = Intent(this, DetailsActivity::class.java)
                intent.putExtra("movieItem", item)
                startActivity(intent)
            }

            view.setOnLongClickListener {
                if (FavoritesActivity.favouritesList.contains(item)) {
                    Toast.makeText(this, "Уже в избранном", Toast.LENGTH_SHORT).show()
                } else {
                    FavoritesActivity.favouritesList.add(item)
                    Toast.makeText(this, "Добавлено в избранное", Toast.LENGTH_SHORT).show()
                }
                true
            }
        }
    }
}
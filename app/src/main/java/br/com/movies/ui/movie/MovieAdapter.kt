package br.com.movies.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.movies.R
import br.com.movies.databinding.ItemMovieBinding
import br.com.movies.model.Movie
import br.com.movies.util.AdapterItemsContract


class MovieAdapter (var movies: List<Movie>, private val callback: ((Movie) -> Unit)?) : RecyclerView.Adapter<MovieAdapter.ViewHolder>(),
    AdapterItemsContract{

    override fun replaceItems(list: List<*>) {
        this.movies = list as List<Movie>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        val binding = ItemMovieBinding.bind(view)
        return ViewHolder(binding)
    }
    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position], callback)
    }

    class ViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie, callback: ((Movie) -> Unit)?) {
            binding.movie = movie
            binding.root.setOnClickListener {
                callback?.invoke(movie)
            }
        }
    }
}
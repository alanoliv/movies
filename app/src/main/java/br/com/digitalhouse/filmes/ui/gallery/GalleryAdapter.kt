package br.com.digitalhouse.filmes.ui.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.digitalhouse.filmes.R
import br.com.digitalhouse.filmes.databinding.ItemGalleryBinding
import br.com.digitalhouse.filmes.model.Movie
import br.com.digitalhouse.filmes.util.AdapterItemsContract


class GalleryAdapter (var movies: List<Movie>, private val callback: ((Movie) -> Unit)?) : RecyclerView.Adapter<GalleryAdapter.ViewHolder>(),
    AdapterItemsContract{

    override fun replaceItems(list: List<*>) {
        this.movies = list as List<Movie>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_gallery, parent, false)
        val binding = ItemGalleryBinding.bind(view)
        return ViewHolder(binding)
    }
    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position], callback)
    }

    class ViewHolder(private val binding: ItemGalleryBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie, callback: ((Movie) -> Unit)?) {
            binding.movie = movie
            binding.root.setOnClickListener {
                callback?.invoke(movie)
            }
        }
    }
}
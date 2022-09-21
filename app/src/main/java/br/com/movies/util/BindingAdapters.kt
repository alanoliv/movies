package br.com.movies.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class BindingAdapters {
    companion object {
        @BindingAdapter("items")
        @JvmStatic
        fun setItems(recyclerView: RecyclerView, items: MutableList<Any>) {

            recyclerView.adapter.let {
                if (it is AdapterItemsContract) {
                    it.replaceItems(items)
                }
            }
        }

        @BindingAdapter( "glideLoad")
        @JvmStatic
        fun loadRemoteImage(iv: ImageView, url: String ){
            Glide.with(iv).load(url).into(iv)
        }
    }
}
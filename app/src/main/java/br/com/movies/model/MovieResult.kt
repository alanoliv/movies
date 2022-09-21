package br.com.movies.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class MovieResult (
    @Expose
    var results: List<Movie>? = null,
    @SerializedName("total_pages")
    var totalPages: Long? = null,
    @SerializedName("total_results")
    var totalResults: Long? = null
) : Serializable
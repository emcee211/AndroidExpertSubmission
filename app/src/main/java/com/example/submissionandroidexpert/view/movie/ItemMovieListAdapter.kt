package com.example.submissionandroidexpert.view.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.submissionandroidexpert.R
import com.example.submissionandroidexpert.data.source.local.entity.MovieEntity
import com.example.submissionandroidexpert.databinding.ItemMovieListBinding
import com.example.submissionandroidexpert.utils.Constant
import com.example.submissionandroidexpert.view.detailmovie.DetailMovieActivity

class ItemMovieListAdapter :
    PagedListAdapter<MovieEntity, ItemMovieListAdapter.ItemMovieListViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemMovieListViewHolder {
        val binding =
            ItemMovieListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemMovieListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemMovieListViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }

    class ItemMovieListViewHolder(private val binding: ItemMovieListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(Constant.BASE_IMAGE_URL + movie.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading_24)
                            .error(R.drawable.ic_error_24)
                    )
                    .into(imgItemPhoto)
                tvItemTitle.text = movie.title
                tvItemRating.text = movie.rating.toString()
                rbItemRating.rating = movie.rating.toFloat() / 2
                itemView.setOnClickListener {
                    val pIintent = Intent(itemView.context, DetailMovieActivity::class.java)
                    pIintent.putExtra(DetailMovieActivity.EXTRA_MOVIE, movie.movieId)
                    itemView.context.startActivity(pIintent)
                }
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.movieId == newItem.movieId
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

}
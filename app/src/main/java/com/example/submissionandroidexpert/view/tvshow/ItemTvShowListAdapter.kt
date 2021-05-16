package com.example.submissionandroidexpert.view.tvshow

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.submissionandroidexpert.R
import com.example.submissionandroidexpert.data.source.local.entity.TvShowEntity
import com.example.submissionandroidexpert.databinding.ItemMovieListBinding
import com.example.submissionandroidexpert.utils.Constant
import com.example.submissionandroidexpert.view.detailtvshow.DetailTvShowActivity

class ItemTvShowListAdapter :
    PagedListAdapter<TvShowEntity, ItemTvShowListAdapter.ItemTvShowListViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemTvShowListViewHolder {
        val binding =
            ItemMovieListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemTvShowListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemTvShowListViewHolder, position: Int) {
        val tvshow = getItem(position)
        if (tvshow != null) {
            holder.bind(tvshow)
        }
    }

    class ItemTvShowListViewHolder(private val binding: ItemMovieListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvshow: TvShowEntity) {
            Log.d("DEBUGADAPTER", "bind: ")
            with(binding) {
                Glide.with(itemView.context)
                    .load(Constant.BASE_IMAGE_URL + tvshow.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading_24)
                            .error(R.drawable.ic_error_24)
                    )
                    .into(imgItemPhoto)
                tvItemTitle.text = tvshow.title
                tvItemRating.text = tvshow.rating.toString()
                rbItemRating.rating = tvshow.rating.toFloat() / 2
                itemView.setOnClickListener {
                    val pIintent = Intent(itemView.context, DetailTvShowActivity::class.java)
                    pIintent.putExtra(DetailTvShowActivity.EXTRA_TVSHOW, tvshow.tvShowId)
                    itemView.context.startActivity(pIintent)
                }
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.tvShowId == newItem.tvShowId
            }

            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

}
package com.example.submissionandroidexpert.view.tvshow

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.submissionandroidexpert.core.R
import com.example.submissionandroidexpert.core.databinding.ItemMovieListBinding
import com.example.submissionandroidexpert.core.utils.Constant
import com.example.submissionandroidexpert.model.TvShow
import com.example.submissionandroidexpert.view.detailtvshow.DetailTvShowActivity

class ItemTvShowListAdapter :
    RecyclerView.Adapter<ItemTvShowListAdapter.ItemTvShowListViewHolder>() {

    private lateinit var list: List<TvShow>

    fun setList(list: List<TvShow>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemTvShowListViewHolder {
        val binding =
            ItemMovieListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemTvShowListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemTvShowListViewHolder, position: Int) {
        val tvshow = list[position]
        holder.bind(tvshow)
    }

    class ItemTvShowListViewHolder(private val binding: ItemMovieListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvshow: TvShow) {
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

    override fun getItemCount(): Int {
        return if (this::list.isInitialized) {
            list.size
        } else 0
    }
}
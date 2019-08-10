package com.example.techflaketask.ui.Home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.example.techflaketask.R
import com.example.techflaketask.data.api.response.Data
import kotlinx.android.synthetic.main.item_gifs.view.*
import com.bumptech.glide.request.RequestOptions
import com.example.techflaketask.data.repositary.Rating
import com.example.techflaketask.ui.detail.DetailActivity


class TrendingAdapter(var context: Context, var onupVote: onUpVote, var ondownVote: onDownVote) :
    RecyclerView.Adapter<ViewHolder>() {

    private var gifsList: ArrayList<Data> = arrayListOf()

    val requestOptions = RequestOptions().placeholder(R.drawable.placeholder).fitCenter()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_gifs, parent, false)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return gifsList.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(gifsList[position], context, requestOptions)

        holder.itemView.imgUpVote.setOnClickListener {
            val rating = Rating(gifsList[position].id, 1, 0)
            onupVote.onUpVoteClick(rating, position)

        }
        holder.itemView.imgDownVote.setOnClickListener {
            val rating = Rating(gifsList[position].id, 0, 1)
            ondownVote.onDownVoteClick(rating, position)
        }

        holder.itemView.setOnClickListener {
            context.startActivity(
                Intent(context, DetailActivity::class.java).putExtra(
                    "video_url",
                    gifsList[position].images.original.mp4
                )
            )
        }
    }

    fun onDataAvailable(
        list: ArrayList<Data>

    ) {

        gifsList = list
        notifyDataSetChanged()
    }

    interface onUpVote {
        fun onUpVoteClick(rating: Rating, position: Int)

    }

    interface onDownVote {
        fun onDownVoteClick(rating: Rating, position: Int)

    }
}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(
        data: Data,
        context: Context,
        requestOptions: RequestOptions

    ) {
        itemView.imgUpVote.setImageResource(R.drawable.ic_like)
        itemView.imgDownVote.setImageResource(R.drawable.ic_like)



        if (data.isUpvote == 1) {
            itemView.imgUpVote.setImageResource(R.drawable.ic_like_selected)
            itemView.imgDownVote.setImageResource(R.drawable.ic_like)
        } else if (data.isDownVote == 1) {
            itemView.imgDownVote.setImageResource(R.drawable.ic_like_selected)
            itemView.imgUpVote.setImageResource(R.drawable.ic_like)
        }
        Glide.with(context)
            .setDefaultRequestOptions(requestOptions)
            .load(data.images.original.url)
            .into(itemView.imgGifs)
    }

}

package br.com.tmoura.flickrsample.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import br.com.tmoura.domain.model.FlickrImage
import br.com.tmoura.flickrsample.R
import com.bumptech.glide.Glide

class FlickrImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val contentView by lazy { itemView.findViewById<ImageView>(R.id.contentView) }

    fun bind(item: FlickrImage) {
        Glide.with(itemView).load(item.url).into(contentView)
    }
}

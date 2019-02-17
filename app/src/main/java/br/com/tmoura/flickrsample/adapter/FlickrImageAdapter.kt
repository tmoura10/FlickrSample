package br.com.tmoura.flickrsample.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import br.com.tmoura.flickrsample.R
import br.com.tmoura.flickrsample.model.FlickrImageItems
import br.com.tmoura.flickrsample.holder.FlickrImageViewHolder

class FlickrImageAdapter(
    var imageItems: FlickrImageItems = FlickrImageItems.empty()
) : RecyclerView.Adapter<FlickrImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlickrImageViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.image_item_view, parent, false)
        return FlickrImageViewHolder(itemView)
    }

    override fun getItemCount() = imageItems.items.size

    override fun onBindViewHolder(viewHolder: FlickrImageViewHolder, position: Int) {
        imageItems.let { viewHolder.bind(it.items[position]) }
    }

    fun update(imageItems: FlickrImageItems) {
        when {
            this.imageItems.term == imageItems.term -> addItems(imageItems)
            else -> setItems(imageItems)
        }
    }

    private fun setItems(imageItems: FlickrImageItems) {
        this.imageItems = imageItems
        notifyDataSetChanged()
    }

    private fun addItems(imageItems: FlickrImageItems) {
        val startPosition = this.itemCount
        this.imageItems = FlickrImageItems(
            term = this.imageItems.term,
            items = this.imageItems.items.plus(imageItems.items)
        )
        notifyItemRangeInserted(startPosition, imageItems.items.size)
    }

}

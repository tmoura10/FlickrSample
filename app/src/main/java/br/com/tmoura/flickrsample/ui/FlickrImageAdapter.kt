package br.com.tmoura.flickrsample.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import br.com.tmoura.flickrsample.R
import br.com.tmoura.flickrsample.model.FlickrImageItems

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
        val startPosition: Int
        if (this.imageItems.term == imageItems.term) {
            startPosition = this.itemCount
            this.imageItems = FlickrImageItems(
                term = this.imageItems.term,
                items = this.imageItems.items.plus(imageItems.items)
            )
        } else {
            startPosition = 0
            this.imageItems = imageItems
        }
        notifyItemRangeInserted(startPosition, imageItems.items.size)
    }

}

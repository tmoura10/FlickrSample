package br.com.tmoura.flickrsample.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import br.com.tmoura.domain.model.FlickrImage
import br.com.tmoura.flickrsample.R

class FlickrImageAdapter(
    private val items: MutableList<FlickrImage>
) : RecyclerView.Adapter<FlickrImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlickrImageViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.image_item_view, parent, false)
        return FlickrImageViewHolder(itemView)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(viewHolder: FlickrImageViewHolder, position: Int) {
        viewHolder.bind(items[position])
    }

    fun add(items: List<FlickrImage>) {
        this.items.addAll(items)
    }

    fun clear() {
        items.clear()
    }
}

package br.com.tmoura.flickrsample.listener

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

class FlickrImageListScrollListener(private val listener: () -> Unit) : RecyclerView.OnScrollListener() {

    private val threshold = 6
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val layoutManager = recyclerView.layoutManager
        when (layoutManager) {
            is LinearLayoutManager -> {
                if (isEndOfListReached(layoutManager)) {
                    listener()
                }
            }
            else -> {} // Do nothing
        }
    }

    private fun isEndOfListReached(layoutManager: LinearLayoutManager): Boolean {
        val count = layoutManager.itemCount
        val lastVisibleItem = layoutManager.findLastVisibleItemPosition()
        return count <= lastVisibleItem + threshold
    }
}

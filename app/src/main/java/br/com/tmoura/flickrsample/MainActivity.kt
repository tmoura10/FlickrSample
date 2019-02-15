package br.com.tmoura.flickrsample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import br.com.tmoura.domain.model.FlickrImage
import br.com.tmoura.flickrsample.ui.FlickrImageAdapter
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class MainActivity : AppCompatActivity(), FlickrList.View {

    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.imageList) }
    private val adapter by lazy { FlickrImageAdapter(items = mutableListOf()) }
    private val columns = 3
    private val searchSubject: PublishSubject<String> = PublishSubject.create()
    private val presenter = FlickrListPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = GridLayoutManager(this, columns)
        recyclerView.adapter = adapter

        //Sample before Search implementation.
        searchSubject.onNext("dog")
    }

    override fun showItems(items: List<FlickrImage>) {
        adapter.add(items)
        adapter.notifyDataSetChanged()
    }

    override fun onSearch(): Observable<String> = searchSubject.hide()

    override fun showError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

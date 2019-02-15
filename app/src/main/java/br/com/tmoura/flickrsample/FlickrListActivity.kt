package br.com.tmoura.flickrsample

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import br.com.tmoura.domain.model.FlickrImage
import br.com.tmoura.flickrsample.di.scopes.PerActivity
import br.com.tmoura.flickrsample.ui.FlickrImageAdapter
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

//TODO Remove view implementation from activity
class FlickrListActivity : DaggerAppCompatActivity(), FlickrList.View {

    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.imageList) }
    private val adapter by lazy { FlickrImageAdapter(items = mutableListOf()) }
    private val columns = 3
    private val searchSubject: PublishSubject<String> = PublishSubject.create()
    @Inject lateinit var presenter: FlickrList.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.subscribe()
        setupViews()
    }

    private fun setupViews() {
        recyclerView.layoutManager = GridLayoutManager(this, columns)
        recyclerView.adapter = adapter

        //Sample before Search implementation.
        searchSubject.onNext("dog")
    }

    override fun showItems(items: List<FlickrImage>) {
        adapter.add(items)
        adapter.notifyDataSetChanged()
    }

    override fun onSearch(): Observable<String> = searchSubject.hide().distinctUntilChanged()

    override fun showError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

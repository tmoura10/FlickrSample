package br.com.tmoura.flickrsample.activity

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.Menu
import android.widget.Toast
import br.com.tmoura.flickrsample.contract.FlickrList
import br.com.tmoura.flickrsample.R
import br.com.tmoura.flickrsample.model.FlickrImageItems
import br.com.tmoura.flickrsample.utils.throttle
import br.com.tmoura.flickrsample.listener.FlickrImageListScrollListener
import br.com.tmoura.flickrsample.adapter.FlickrImageAdapter
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

//TODO Remove view implementation from activity
class FlickrListActivity : DaggerAppCompatActivity(), FlickrList.View {

    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.imageList) }
    private val adapter by lazy { FlickrImageAdapter() }
    private val columns = 3
    private val searchSubject: PublishSubject<String> = PublishSubject.create()
    private val scrollToEndOfListSubject: PublishSubject<Unit> = PublishSubject.create()

    @Inject
    lateinit var presenter: FlickrList.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.subscribe()
        setupViews()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.flickr_list_menu, menu)
        setupSearchView(menu)
        return true
    }

    private fun setupSearchView(menu: Menu?) {
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu?.findItem(R.id.search)
        val searchView = searchItem?.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchSubject.onNext(query ?: "")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Do nothing
                return true
            }
        })
    }

    private fun setupViews() {
        recyclerView.layoutManager = GridLayoutManager(this, columns)
        recyclerView.adapter = adapter
        recyclerView.addOnScrollListener(FlickrImageListScrollListener {
            scrollToEndOfListSubject.onNext(Unit)
        })
    }

    override fun showItems(items: FlickrImageItems) {
        adapter.update(items)
    }

    override fun onSearch(): Observable<String> =
        searchSubject.hide().throttle()

    override fun onScrolledToEnd(): Observable<FlickrImageItems> =
        scrollToEndOfListSubject
            .hide()
            .map { adapter.imageItems }
            .throttle()

    override fun showError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

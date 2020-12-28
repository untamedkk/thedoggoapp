package com.thedogapp.ui.breedsearch

import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.doogosearch.viewmodel.SearchViewModel
import com.thedogapp.R
import com.thedogapp.ui.imagesearch.ImageSearchActivity

class BreedSearchActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    lateinit var searchViewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_breed_search)
        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)

        val searchEditText = findViewById<SearchView>(R.id.edit_text_search_bar)
        searchEditText.setOnQueryTextListener(this)

        val recyclerAdapter = SearchBreedAdapter()

        findViewById<RecyclerView>(R.id.recycler_search_list).apply {
            layoutManager = LinearLayoutManager(this@BreedSearchActivity)
            addItemDecoration(
                DividerItemDecoration(
                    this@BreedSearchActivity,
                    DividerItemDecoration.VERTICAL
                )
            )
            adapter = recyclerAdapter
        }

        searchViewModel.breedSearchViewModel.observe(this, Observer { it ->
            recyclerAdapter.setItems(it)
        })

        searchViewModel.error.observe(this, Observer { showToast(it) })

    }

    private fun doSearch(query: String) {
        if (TextUtils.isEmpty(query)) {
            searchViewModel.breedSearchViewModel.value = mutableListOf()
        } else {
            searchViewModel.doBreedSearch(query)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        doSearch(query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        doSearch(newText)
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item1 -> {
                ImageSearchActivity.open(this)
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
package com.thedogapp.ui.imagesearch

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.doogosearch.model.ImageSearch
import com.doogosearch.viewmodel.SearchViewModel
import com.thedogapp.R

class ImageSearchActivity : AppCompatActivity() {

    private val PAGE_SIZE = 10

    lateinit var searchViewModel: SearchViewModel
    private var page: Int = 1
    private var totalPage: Int = 2
    private var isLastPage = false
    private var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_search)

        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)

        val recyclerAdapter = ImageSearchAdapter()
        val layoutManagerObj = LinearLayoutManager(this@ImageSearchActivity)

        findViewById<RecyclerView>(R.id.recycler_image_list).apply {
            layoutManager = layoutManagerObj
            addItemDecoration(
                DividerItemDecoration(
                    this@ImageSearchActivity,
                    DividerItemDecoration.VERTICAL
                )
            )
            addOnScrollListener(object : PaginationScrollListener(layoutManagerObj) {
                override fun loadMoreItems() {
                    isLoading = true
                    ++page
                    searchViewModel.doImageSearch(PAGE_SIZE, page, ImageSearch.ORDER.ASC)
                }

                override fun getTotalPageCount(): Int {
                    return totalPage
                }

                override fun isLastPage(): Boolean {
                    return isLastPage
                }

                override fun isLoading(): Boolean {
                    return isLoading
                }

            })
            adapter = recyclerAdapter
        }

        searchViewModel.imageSearchViewModel.observe(
            this,
            Observer {
                isLastPage = it.size < PAGE_SIZE
                if (it.size == PAGE_SIZE) {
                    ++totalPage
                }
                isLoading = false
                recyclerAdapter.addItems(it)
            })

        searchViewModel.doImageSearch(PAGE_SIZE, page, ImageSearch.ORDER.ASC)

    }

    companion object {
        fun open(context: Context) {
            context.startActivity(Intent(context, ImageSearchActivity::class.java))
        }
    }
}
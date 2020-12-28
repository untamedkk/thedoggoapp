package com.thedogapp.ui.breedsearch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.doogosearch.model.BreedSearch
import com.thedogapp.R
import com.thedogapp.ui.BaseAdapter

class SearchBreedAdapter : BaseAdapter<BreedSearch>() {

    class SearchViewHolder(itemView: View) : BaseViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.text_view_dog_breed_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val itemeView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_breed_search, parent, false)
        return SearchViewHolder(itemeView)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        (holder as SearchViewHolder).textView.text = searches[position].name
    }
}
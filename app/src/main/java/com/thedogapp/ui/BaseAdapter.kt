package com.thedogapp.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

open class BaseAdapter<T> : RecyclerView.Adapter<BaseAdapter.BaseViewHolder>() {

    protected var searches: MutableList<T> = mutableListOf()

    open class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    fun setItems(searches: MutableList<T>) {
        this.searches = searches
        notifyDataSetChanged()
    }

    fun addItems(searches: MutableList<T>) {
        this.searches.addAll(searches)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return searches.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(parent)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
    }
}
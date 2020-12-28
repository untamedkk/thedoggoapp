package com.thedogapp.ui.imagesearch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.doogosearch.model.ImageSearch
import com.thedogapp.R
import com.thedogapp.ui.BaseAdapter

class ImageSearchAdapter : BaseAdapter<ImageSearch>() {

    class ImageSearchViewHolder(itemView: View) : BaseViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.text_view_dog_breed_name)
        val imageView: ImageView = itemView.findViewById(R.id.image_view_dog_breed)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageSearchViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_search_image, parent, false)
        return ImageSearchViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        (holder as ImageSearchViewHolder)

        val searchResult = searches[position]

        searchResult.breeds.apply {
            if (size > 0) {
                holder.textView.text = String.format("%d :: %s", (position + 1), this@apply[0].name)
            }
        }

        Glide.with(holder.imageView.context).load(searchResult.url).centerCrop()
            .placeholder(R.mipmap.ic_launcher_round).into(holder.imageView)
    }
}
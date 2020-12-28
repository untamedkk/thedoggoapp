package com.doogosearch.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.doogosearch.model.BreedSearch
import com.doogosearch.model.ImageSearch
import com.doogosearch.repository.SearchRepository

class SearchViewModel : ViewModel() {

    var breedSearchViewModel = MutableLiveData<MutableList<BreedSearch>>()

    var imageSearchViewModel = MutableLiveData<MutableList<ImageSearch>>()

    var error = MutableLiveData<String>()

    var isLoading = MutableLiveData<Boolean>()

    private val ERROR_MESSAGE = "Error while fetching data..."

    init {
        breedSearchViewModel.value = mutableListOf()
        imageSearchViewModel.value = mutableListOf()
    }

    fun doBreedSearch(query: String) {
        isLoading.value = true
        SearchRepository.doBreedSearch(query, object : SearchRepository.OnSearch<BreedSearch> {
            override fun onSuccess(data: MutableList<BreedSearch>) {
                breedSearchViewModel.value = data
                isLoading.value = false
            }

            override fun onFailure() {
                isLoading.value = false
                error.value = ERROR_MESSAGE
            }
        })
    }

    fun doImageSearch(limit: Int, page: Int, order: ImageSearch.ORDER) {
        isLoading.value = true
        SearchRepository.doImageSearch(
            limit,
            page,
            order,
            object : SearchRepository.OnSearch<ImageSearch> {
                override fun onSuccess(data: MutableList<ImageSearch>) {
                    isLoading.value = false
                    imageSearchViewModel.value = data
                }

                override fun onFailure() {
                    isLoading.value = false
                    error.value = ERROR_MESSAGE
                }
            })
    }
}
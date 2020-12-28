package com.doogosearch.model

import java.io.Serializable

class ImageSearch(val breeds: List<Search>, val id: String, val url: String, width: Int, height: Int) :
    BaseSearch,
    Serializable {

    enum class ORDER { DESC, ASC, RANDOM }
}

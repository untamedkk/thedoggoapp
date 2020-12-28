package com.doogosearch.model

import java.io.Serializable

class BreedSearch(
    bredFor: String,
    breedGroup: String,
    lifeSpan: String,
    height: Height,
    id: Int,
    name: String,
    temperament: String
) : Search(bredFor, breedGroup, lifeSpan, height, id, name, temperament), Serializable
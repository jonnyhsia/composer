package com.jonnyhsia.model.home.entity

data class Inspirations(
        val inspirations: List<Inspiration>
) {

    data class Inspiration(
            val image: String,
            val quote: String
    )

}
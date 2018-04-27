package com.jonnyhsia.model.home.entity

data class Topic(
        val id: Int,
        val name: String,
        val description: String,
        val coverImage: String
) {
    companion object {
        fun template(name: String, coverImage: String = ""): Topic {
            return Topic(0, name, "", coverImage)
        }
    }
}
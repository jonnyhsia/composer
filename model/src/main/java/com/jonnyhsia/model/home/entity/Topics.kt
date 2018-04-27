package com.jonnyhsia.model.home.entity

data class Topics(
        val topics: List<Topic>
) {

    companion object {
        fun sample(strings: List<String>): Topics {
            val topics = ArrayList<Topic>(strings.size)
            for (string in strings) {
                val elements = string.split("#")
                topics.add(Topic.template(elements[0], elements[1]))
            }
            return Topics(topics)
        }
    }
}
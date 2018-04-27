package com.jonnyhsia.model.home.entity

data class Advertisements(
        val advertisementList: List<Advertisement>
) {

    data class Advertisement(
            val adId: Int,
            val adName: String,
            val adDescription: String,
            val adUrl: String,
            val adImage: String,
            val adThemeColor: String,
            val ownerId: Int,
            val ownerName: String
    )

    companion object {

        fun sample(advertisementsStringList: List<String>): Advertisements {
            val advertisementList = ArrayList<Advertisement>(advertisementsStringList.size)
            for (string in advertisementsStringList) {
                string.split("#").run {
                    advertisementList.add(Advertisement(0, get(0), get(1), "", get(2), "#${get(3)}", 1, get(4)))
                }
            }
            return Advertisements(advertisementList)
        }
    }
}
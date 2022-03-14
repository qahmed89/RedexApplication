package com.ahmed.networkclean.feature_network.domain.model

import org.json.JSONObject

data class PostsItem(
    val id: Int,
    val title: String
){
    fun mapFromJson (data : String) {
        val jsonData = JSONObject (data)

    }
}
package com.krkhvr.gamemvvmca.domain.model

import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

data class Post(
    var id: String = "",
    var name: String = "",
    var description: String = "",
    var category: String = "",
    var image: String = "",
    var userId: String = "",
    var user: User? = null,
    var likes: ArrayList<String> = ArrayList()
) {
    fun toJson(): String =
        Gson().toJson(
            Post(
                id = id,
                name = name,
                description = description,
                category = category,
                image = if (image != "") URLEncoder.encode(
                    image,
                    StandardCharsets.UTF_8.toString()
                ) else "",
                userId = userId,
                user = User(
                    id = user?.id ?: "",
                    username = user?.username ?: "",
                    email = user?.email ?: "",
                    image = if (!user?.image.isNullOrEmpty()) URLEncoder.encode(
                        user?.image,
                        StandardCharsets.UTF_8.toString()
                    ) else ""
                ),
                likes = likes
            )
        )

    companion object {
        fun fromJson(data: String): Post =
            Gson().fromJson(data, Post::class.java)
    }
}

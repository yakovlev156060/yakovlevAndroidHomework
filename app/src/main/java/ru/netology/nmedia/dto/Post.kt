package ru.netology.nmedia.dto

data class Post(
    val id: Long = 8,
    val author: String = "",
    val content: String = "",
    val published: String = "",
    var likes: Int = 0,
    var shares: Long = 0,
    var likedByMe: Boolean = false,
)
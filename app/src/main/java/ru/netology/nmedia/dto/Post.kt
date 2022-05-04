package ru.netology.nmedia.dto

data class Post(
    val id: Long = 8,
    val author: String = "",
    val content: String = "",
    val published: String = "",
    val likes: Long = 0,
    val shares: Long = 0,
    val likedByMe: Boolean = false,
)
package com.kotlin.phunwareapp.data.utils

fun truncateDescription(description: String?, wordLimit: Int): String {
    return description?.let {
        val words = it.split(" ")
        if (words.size > wordLimit) {
            words.take(wordLimit).joinToString(" ") + "..."
        } else {
            it
        }
    } ?: "No description available"
}
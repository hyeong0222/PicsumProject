package com.example.picsumproject.navigation

enum class Screens() {
    MAIN,
    DETAIL;

    operator fun invoke(): String {
        return name
    }
}
package com.muhammadfauzanazhar.trpl5bcourse

sealed class Routes(val route: String) {
    object Home : Routes("home")
    object Profile : Routes("profile")
    object Setting : Routes("setting")
    object Quotes : Routes("quotes")
}
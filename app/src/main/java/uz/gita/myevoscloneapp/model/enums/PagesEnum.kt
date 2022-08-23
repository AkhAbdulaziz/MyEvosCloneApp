package uz.gita.myevoscloneapp.model.enums

import uz.gita.myevoscloneapp.R

enum class PagesEnum {
    MAIN, FAVOURITES, MENU, RESTAURANTS, PROFILE;

    fun getPageIndex(): Int {
        return when (this) {
            FAVOURITES -> 1
            MENU -> 2
            RESTAURANTS -> 3
            PROFILE -> 4
            else -> 0
        }
    }

    fun getId(): Int {
        return when (this) {
            FAVOURITES -> R.id.favourite
            MENU ->  R.id.food_menu
            RESTAURANTS -> R.id.restaurants
            PROFILE -> R.id.profile
            else -> R.id.main
        }
    }
}
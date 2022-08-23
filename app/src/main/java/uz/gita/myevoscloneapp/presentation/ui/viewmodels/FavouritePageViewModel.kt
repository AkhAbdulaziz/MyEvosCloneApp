package uz.gita.myevoscloneapp.presentation.ui.viewmodels

import uz.gita.myevoscloneapp.model.data.FoodData

interface FavouritePageViewModel {
    fun addFood(foodData: FoodData, count : Int)
    fun removeFood(foodData: FoodData)
    fun getSelectedFoods(): List<FoodData>
    fun getAllPopularFoods(): List<FoodData>

    fun changeUserFavouriteFoodData(foodData: FoodData, boolean: Boolean)
    fun clearUserFavouritesList()
    fun getUserFavouritesList() : List<FoodData>
}
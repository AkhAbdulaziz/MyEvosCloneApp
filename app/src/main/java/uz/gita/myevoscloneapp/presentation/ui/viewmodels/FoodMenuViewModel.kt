package uz.gita.myevoscloneapp.presentation.ui.viewmodels

import uz.gita.myevoscloneapp.model.data.FoodData

interface FoodMenuViewModel {
    fun addFood(foodData: FoodData, count: Int)
    fun getAllFoods() : List<FoodData>
}
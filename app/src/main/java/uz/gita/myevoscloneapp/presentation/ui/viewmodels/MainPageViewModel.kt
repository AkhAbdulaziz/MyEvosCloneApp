package uz.gita.myevoscloneapp.presentation.ui.viewmodels

import uz.gita.myevoscloneapp.model.data.AdsData
import uz.gita.myevoscloneapp.model.data.FoodData

interface MainPageViewModel {
    fun getAllAds(): List<AdsData>
    fun getAllPopularFoods(): List<FoodData>
    fun addFood(foodData: FoodData, count : Int)
}
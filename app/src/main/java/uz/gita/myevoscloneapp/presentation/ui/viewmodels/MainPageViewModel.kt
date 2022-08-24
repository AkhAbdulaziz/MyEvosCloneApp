package uz.gita.myevoscloneapp.presentation.ui.viewmodels

import androidx.lifecycle.LiveData
import uz.gita.myevoscloneapp.model.data.AdsData
import uz.gita.myevoscloneapp.model.data.FoodData

interface MainPageViewModel {
    val allAdsLiveData: LiveData<List<AdsData>>
    val allPopularFoodsLiveData: LiveData<List<FoodData>>

    fun getAllAds()
    fun getAllPopularFoods()
    fun addFood(foodData: FoodData, count: Int)
}
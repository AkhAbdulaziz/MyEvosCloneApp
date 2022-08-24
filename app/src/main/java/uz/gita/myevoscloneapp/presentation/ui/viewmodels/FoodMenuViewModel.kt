package uz.gita.myevoscloneapp.presentation.ui.viewmodels

import androidx.lifecycle.LiveData
import uz.gita.myevoscloneapp.model.data.FoodData

interface FoodMenuViewModel {
    val allFoodsLiveData: LiveData<List<FoodData>>

    fun addFood(foodData: FoodData, count: Int)
    fun getAllFoods()
}
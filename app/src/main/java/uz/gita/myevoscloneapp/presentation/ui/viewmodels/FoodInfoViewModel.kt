package uz.gita.myevoscloneapp.presentation.ui.viewmodels

import androidx.lifecycle.LiveData
import uz.gita.myevoscloneapp.model.data.FoodData

interface FoodInfoViewModel {
    val updateFoodLiveData: LiveData<Unit>

//    fun setSelectedFoodsListener(block: (List<FoodData>) -> Unit)

    val selectedFoodsLiveData: LiveData<List<FoodData>>

    fun addFood(foodData: FoodData, count: Int)

    fun getSelectedFoods()

    fun changeUserFavouriteFoodData(foodData: FoodData, boolean: Boolean)

    fun getUserFavouritesList() : List<FoodData>
}
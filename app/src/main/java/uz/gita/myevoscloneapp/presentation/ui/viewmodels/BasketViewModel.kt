package uz.gita.myevoscloneapp.presentation.ui.viewmodels

import androidx.lifecycle.LiveData
import uz.gita.myevoscloneapp.model.data.FoodData
import uz.gita.myevoscloneapp.model.enums.PagesEnum

interface BasketViewModel {
    val selectedFoodsLiveData: LiveData<List<FoodData>>

    fun getSelectedFoods()

    fun addFood(foodData: FoodData, count: Int)

    fun changePage(pagesEnum: PagesEnum)
}
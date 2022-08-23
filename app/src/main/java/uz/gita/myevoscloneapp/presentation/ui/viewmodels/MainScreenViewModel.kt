package uz.gita.myevoscloneapp.presentation.ui.viewmodels

import androidx.lifecycle.LiveData
import uz.gita.myevoscloneapp.model.data.FoodData
import uz.gita.myevoscloneapp.model.enums.PagesEnum

interface MainScreenViewModel {
    val selectedFoodsLiveData: LiveData<List<FoodData>>

    val changePageLiveData: LiveData<PagesEnum>

    fun getSelectedFoods()

    fun clearSelectedFoodsList()
}
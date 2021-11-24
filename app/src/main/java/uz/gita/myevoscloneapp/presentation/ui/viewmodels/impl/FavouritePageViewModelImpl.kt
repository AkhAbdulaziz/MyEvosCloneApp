package uz.gita.myevoscloneapp.presentation.ui.viewmodels.impl

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.myevoscloneapp.domain.repository.AppRepository
import uz.gita.myevoscloneapp.model.data.FoodData
import uz.gita.myevoscloneapp.presentation.ui.viewmodels.FavouritePageViewModel
import javax.inject.Inject

@HiltViewModel
class FavouritePageViewModelImpl @Inject constructor(private val appRepository: AppRepository) :
    ViewModel(), FavouritePageViewModel {

    override fun addFood(foodData: FoodData) {
        appRepository.addFood(foodData)
    }

    override fun removeFood(foodData: FoodData) {
        appRepository.removeFood(foodData)
    }

    override fun getSelectedFoods(): List<FoodData> {
        return appRepository.selectedFoodList
    }

    override fun getAllPopularFoods(): List<FoodData> {
        return appRepository.foodsData.filter { it.isFavourite }
    }

    override fun changeUserFavouriteFoodData(foodData: FoodData, boolean: Boolean) {
        appRepository.changeUserFavouriteFoodData(foodData, boolean)
    }

    override fun getUserFavouritesList(): List<FoodData> {
        return appRepository.getUserFavouritesList()
    }

    override fun clearUserFavouritesList() {
        appRepository.clearUserFavouritesList()
    }
}

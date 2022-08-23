package uz.gita.myevoscloneapp.presentation.ui.viewmodels.impl

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.myevoscloneapp.domain.repository.AppRepository
import uz.gita.myevoscloneapp.model.data.FoodData
import uz.gita.myevoscloneapp.presentation.ui.viewmodels.FavouritePageViewModel
import uz.gita.myevoscloneapp.utils.getUniques
import javax.inject.Inject

@HiltViewModel
class FavouritePageViewModelImpl @Inject constructor(private val appRepository: AppRepository) :
    ViewModel(), FavouritePageViewModel {

    override fun addFood(foodData: FoodData, count: Int) {
        appRepository.addFood(foodData, count)
    }

    override fun removeFood(foodData: FoodData) {
        appRepository.removeFood(foodData)
    }

    override fun getSelectedFoods(): List<FoodData> {
        return appRepository.selectedFoodList.getUniques()
    }

    override fun getAllPopularFoods(): List<FoodData> {
        return appRepository.foodsData.getUniques().filter { it.isFavourite }
    }

    override fun changeUserFavouriteFoodData(foodData: FoodData, boolean: Boolean) {
        appRepository.changeUserFavouriteFoodData(foodData, boolean)
    }

    override fun getUserFavouritesList(): List<FoodData> {
        return appRepository.getUserFavouritesList().getUniques()
    }

    override fun clearUserFavouritesList() {
        appRepository.clearUserFavouritesList()
    }
}

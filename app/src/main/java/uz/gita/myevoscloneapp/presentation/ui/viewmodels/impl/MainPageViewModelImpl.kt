package uz.gita.myevoscloneapp.presentation.ui.viewmodels.impl

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.myevoscloneapp.domain.repository.AppRepository
import uz.gita.myevoscloneapp.model.data.AdsData
import uz.gita.myevoscloneapp.model.data.FoodData
import uz.gita.myevoscloneapp.presentation.ui.viewmodels.MainPageViewModel
import uz.gita.myevoscloneapp.utils.getUniqueAds
import uz.gita.myevoscloneapp.utils.getUniques
import javax.inject.Inject

@HiltViewModel
class MainPageViewModelImpl @Inject constructor(private val appRepository: AppRepository) :
    ViewModel(), MainPageViewModel {

    override fun getAllAds(): List<AdsData> {
        return appRepository.adsData.getUniqueAds()
    }

    override fun getAllPopularFoods(): List<FoodData> {
        return appRepository.foodsData.getUniques().filter { it.isFavourite }
    }

    override fun addFood(foodData: FoodData, count : Int) {
        appRepository.addFood(foodData, count)
    }
}

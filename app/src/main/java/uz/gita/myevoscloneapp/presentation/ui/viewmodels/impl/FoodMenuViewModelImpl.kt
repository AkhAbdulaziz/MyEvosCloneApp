package uz.gita.myevoscloneapp.presentation.ui.viewmodels.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.myevoscloneapp.domain.repository.AppRepository
import uz.gita.myevoscloneapp.model.data.FoodData
import uz.gita.myevoscloneapp.presentation.ui.viewmodels.FoodMenuViewModel
import uz.gita.myevoscloneapp.utils.getUniques
import javax.inject.Inject

@HiltViewModel
class FoodMenuViewModelImpl @Inject constructor(private val appRepository: AppRepository) :
    ViewModel(), FoodMenuViewModel {

    override val allFoodsLiveData = MutableLiveData<List<FoodData>>()

    override fun addFood(foodData: FoodData, count: Int) {
        appRepository.addFood(foodData, count)
    }

    override fun getAllFoods() {
        allFoodsLiveData.value = appRepository.foodsData.getUniques()
    }
}
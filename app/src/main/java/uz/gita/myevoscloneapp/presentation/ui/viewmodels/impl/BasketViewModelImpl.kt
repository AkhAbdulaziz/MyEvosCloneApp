package uz.gita.myevoscloneapp.presentation.ui.viewmodels.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.myevoscloneapp.domain.repository.AppRepository
import uz.gita.myevoscloneapp.model.data.FoodData
import uz.gita.myevoscloneapp.model.enums.PagesEnum
import uz.gita.myevoscloneapp.presentation.ui.viewmodels.BasketViewModel
import uz.gita.myevoscloneapp.utils.getUniquesForBasket
import javax.inject.Inject

@HiltViewModel
class BasketViewModelImpl @Inject constructor(private val appRepository: AppRepository) :
    ViewModel(), BasketViewModel {

    override val selectedFoodsLiveData = MutableLiveData<List<FoodData>>()

    override fun getSelectedFoods() {
        selectedFoodsLiveData.value = appRepository.selectedFoodList.getUniquesForBasket()
    }

    override fun addFood(foodData: FoodData, count: Int) {
        appRepository.addFood(foodData, count)
    }

    init {
        appRepository.setOrderChangedListener {
            getSelectedFoods()
        }
    }

    override fun changePage(pagesEnum: PagesEnum) {
        appRepository.changePage(pagesEnum)
    }
}
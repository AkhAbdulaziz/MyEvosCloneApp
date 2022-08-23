package uz.gita.myevoscloneapp.presentation.ui.viewmodels.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.myevoscloneapp.domain.repository.AppRepository
import uz.gita.myevoscloneapp.model.data.FoodData
import uz.gita.myevoscloneapp.model.enums.PagesEnum
import uz.gita.myevoscloneapp.presentation.ui.viewmodels.MainScreenViewModel
import uz.gita.myevoscloneapp.utils.getUniques
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModelImpl @Inject constructor(private val appRepository: AppRepository) :
    ViewModel(), MainScreenViewModel {

    override val selectedFoodsLiveData = MutableLiveData<List<FoodData>>()
    override val changePageLiveData = MutableLiveData<PagesEnum>()

    override fun getSelectedFoods() {
        selectedFoodsLiveData.value = appRepository.selectedFoodList.getUniques()
    }

    override fun clearSelectedFoodsList() {
        appRepository.clearSelectedFoodsList()
    }

    init {
        appRepository.setOrderChangedListener {
            getSelectedFoods()
        }
        appRepository.setChangePageListener {
            changePageLiveData.value = it
        }
    }
}
package uz.gita.myevoscloneapp.presentation.ui.viewmodels.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.myevoscloneapp.domain.repository.AppRepository
import uz.gita.myevoscloneapp.model.data.FoodData
import uz.gita.myevoscloneapp.presentation.ui.viewmodels.FoodInfoViewModel
import uz.gita.myevoscloneapp.utils.getUniques
import javax.inject.Inject

@HiltViewModel
class FoodInfoViewModelImpl @Inject constructor(private val appRepository: AppRepository) :
    ViewModel(), FoodInfoViewModel {

    override val updateFoodLiveData = MutableLiveData<Unit>()

    override val selectedFoodsLiveData = MutableLiveData<List<FoodData>>()

   /* private var selectedFoodsListener: ((List<FoodData>) -> Unit)? = null
    override fun setSelectedFoodsListener(block: (List<FoodData>) -> Unit) {
        selectedFoodsListener = block
    }*/

    override fun getSelectedFoods() {
        selectedFoodsLiveData.value =  appRepository.selectedFoodList.getUniques()
    }

    override fun changeUserFavouriteFoodData(foodData: FoodData, boolean: Boolean) {
        appRepository.changeUserFavouriteFoodData(foodData, boolean)
    }

    override fun getUserFavouritesList(): List<FoodData> {
        return appRepository.getUserFavouritesList().getUniques()
    }

    override fun addFood(foodData: FoodData, count: Int) {
        appRepository.addFood(foodData, count)
    }

    init {
        appRepository.setOrderChangedListener {
            getSelectedFoods()
        }
    }
}
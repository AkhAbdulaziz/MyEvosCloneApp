package uz.gita.myevoscloneapp.presentation.ui.viewmodels.impl

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.myevoscloneapp.domain.repository.AppRepository
import uz.gita.myevoscloneapp.model.data.LocationData
import uz.gita.myevoscloneapp.presentation.ui.viewmodels.RestaurantsViewModel
import uz.gita.myevoscloneapp.utils.getUniqueLocations
import javax.inject.Inject

@HiltViewModel
class RestaurantsViewModelImpl @Inject constructor(private val appRepository: AppRepository) :
    ViewModel(), RestaurantsViewModel {

    override fun getAllLocations(): List<LocationData> {
        return appRepository.locationsData.getUniqueLocations()
    }
}
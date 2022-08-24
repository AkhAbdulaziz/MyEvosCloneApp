package uz.gita.myevoscloneapp.presentation.ui.viewmodels.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.myevoscloneapp.domain.repository.AppRepository
import uz.gita.myevoscloneapp.model.enums.MapTypes
import uz.gita.myevoscloneapp.presentation.ui.viewmodels.MapScreenViewModel
import javax.inject.Inject

@HiltViewModel
class MapScreenViewModelImpl @Inject constructor(private val appRepository: AppRepository) :
    ViewModel(), MapScreenViewModel {
    override val lastMapTypeLiveData = MutableLiveData<MapTypes>()

    override fun lastMapType(mapTypes: MapTypes) {
        appRepository.lastMapType(mapTypes)
    }

    override fun lastMapType(): MapTypes {
        return appRepository.lastMapType()
    }
}
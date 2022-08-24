package uz.gita.myevoscloneapp.presentation.ui.viewmodels

import androidx.lifecycle.LiveData
import uz.gita.myevoscloneapp.model.enums.MapTypes

interface MapScreenViewModel {
    val lastMapTypeLiveData: LiveData<MapTypes>

    fun lastMapType(mapTypes: MapTypes)
    fun lastMapType() : MapTypes
}
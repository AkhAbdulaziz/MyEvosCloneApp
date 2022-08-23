package uz.gita.myevoscloneapp.presentation.ui.viewmodels

import uz.gita.myevoscloneapp.model.data.LocationData

interface RestaurantsViewModel {
    fun getAllLocations() : List<LocationData>
}
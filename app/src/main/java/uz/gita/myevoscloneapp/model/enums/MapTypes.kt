package uz.gita.myevoscloneapp.model.enums

import com.google.android.gms.maps.GoogleMap

enum class MapTypes {
    NORMAL, HYBRID, SATELLITE, TERRAIN, NONE;

    fun getValue(): Int {
        return when (this) {
            NORMAL -> GoogleMap.MAP_TYPE_NORMAL
            HYBRID -> GoogleMap.MAP_TYPE_HYBRID
            SATELLITE -> GoogleMap.MAP_TYPE_SATELLITE
            TERRAIN -> GoogleMap.MAP_TYPE_TERRAIN
            else -> GoogleMap.MAP_TYPE_NONE
        }
    }
}
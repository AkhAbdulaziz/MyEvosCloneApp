package uz.gita.myevoscloneapp.utils

import android.content.res.Resources
import android.util.TypedValue
import com.google.android.gms.maps.GoogleMap
import uz.gita.myevoscloneapp.model.data.AdsData
import uz.gita.myevoscloneapp.model.data.FoodData
import uz.gita.myevoscloneapp.model.data.LocationData
import uz.gita.myevoscloneapp.model.enums.MapTypes

val Int.dp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    )

fun List<Int>.show(): String {
    val sb = StringBuilder()
    for (i in this.indices)
        sb.append("${this[i]}")
    return sb.toString()
}

fun Double.format(digits: Int) = "%.${digits}f".format(this)

fun List<FoodData>.getUniques(): List<FoodData> {
    val resultList = ArrayList<FoodData>()
    val ids: HashMap<Long, Int> = HashMap()
    var index = 0

    for (data in this) {
        if (!ids.containsKey(data.id)) {
            ids[data.id] = index++
            resultList.add(data)
        } else {
            if (data.count != 0) {
                resultList[ids[data.id]!!] = data
            }
        }
    }
    return resultList
}

fun List<FoodData>.getUniquesForBasket(): List<FoodData> {
    val resultList = ArrayList<FoodData>()
    for (data in this) {
        if (!resultList.contains(data) && (data.count != 0)) {
            resultList.add(data)
        }
    }
    return resultList
}

fun List<AdsData>.getUniqueAds(): List<AdsData> {
    val resultList = ArrayList<AdsData>()
    for (data in this) {
        if (!resultList.contains(data)) {
            resultList.add(data)
        }
    }
    resultList.addAll(resultList)   // Doubling
    return resultList
}

fun List<LocationData>.getUniqueLocations(): List<LocationData> {
    val resultList = ArrayList<LocationData>()
    for (data in this) {
        if (!resultList.contains(data)) {
            resultList.add(data)
        }
    }
    return resultList
}

fun Int.getMapType(): MapTypes {
    return when (this) {
        GoogleMap.MAP_TYPE_NORMAL -> MapTypes.NORMAL
        GoogleMap.MAP_TYPE_HYBRID -> MapTypes.HYBRID
        GoogleMap.MAP_TYPE_SATELLITE -> MapTypes.SATELLITE
        GoogleMap.MAP_TYPE_TERRAIN -> MapTypes.TERRAIN
        else -> MapTypes.NONE
    }
}
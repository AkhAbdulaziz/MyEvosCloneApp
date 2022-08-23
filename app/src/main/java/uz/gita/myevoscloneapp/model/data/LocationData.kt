package uz.gita.myevoscloneapp.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocationData(
    val id: Long,
    val name: String,
    val description: String,
    val time: String,
    val latitude: Double,
    val longitude: Double,
    val type: Long
) : Parcelable

enum class LocationEnum(val pos: Long, val text: String) {
    TASHKENT(1, "TOSHKENT"),
    FARGONA(2, "FARG'ONA"),
    QASHQADARYO(3, "QASHQADARYO"),
    ANDIJON(4, "ANDIJON"),
    QOQON(5, "QO'QON"),
    NAMANGAN(6, "NAMANGAN"),
    SAMARQAND(7, "SAMARQAND")
}
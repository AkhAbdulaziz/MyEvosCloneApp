package uz.gita.myevoscloneapp.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocationData(
    val id: Long,
    val name: String,
    val description: String,
    val time: String,
    val latitude: Float,
    val longitude: Float,
    val type: Int
) : Parcelable

enum class LocationEnum(val pos: Int) {
    TASHKENT(1),
    FARGONA(2),
    QASHQADARYO(3),
    ANDIJON(4),
    QOQON(5),
    NAMANGAN(6),
    SAMARQAND(7)
}
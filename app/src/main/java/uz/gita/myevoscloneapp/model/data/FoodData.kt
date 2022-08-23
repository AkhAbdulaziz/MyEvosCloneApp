package uz.gita.myevoscloneapp.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FoodData(
    val id: Long,
    val name: String,
    val cost: Long,
    val imageURL: String,
    val type: Long,
    var isFavourite: Boolean = false,
    var count: Int = 0
) : Parcelable

enum class TypeEnum(
    val pos: Long,
    val text: String
) {
    SET(1, "Set"),
    LAVASH(2, "Lavash"),
    SHAURMA(3, "Shaurma"),
    DONAR(4, "Donar"),
    BURGER(5, "Burger"),
    XOTDOG(6, "Xot-dog"),
    DESERTLAR(7, "Desertlar"),
    ICHIMLIKLAR(8, "Ichimliklar"),
    GAZAKLAR(9, "Gazaklar")
}



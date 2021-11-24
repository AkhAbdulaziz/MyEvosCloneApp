package uz.gita.myevoscloneapp.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AdsData(
    val id: Long,
    val imageURL: String
):Parcelable
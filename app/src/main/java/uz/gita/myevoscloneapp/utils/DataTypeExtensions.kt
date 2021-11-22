package uz.gita.myevoscloneapp.utils

import android.content.res.Resources
import android.util.TypedValue

val Int.dp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    )

fun List<Int>.show() : String {
    val sb = StringBuilder()
    for (i in this.indices)
        sb.append("${this[i]}" ,)
    return sb.toString()
}

fun Double.format(digits: Int) = "%.${digits}f".format(this)


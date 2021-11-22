package uz.gita.myevoscloneapp.utils

import android.content.Context
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

fun color(context: Context, @ColorRes colorResID: Int): Int =
    ContextCompat.getColor(context, colorResID)
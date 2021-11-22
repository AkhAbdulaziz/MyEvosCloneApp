package uz.gita.myevoscloneapp.utils

import android.os.Bundle
import androidx.fragment.app.Fragment

// Fragmentga bundle orqali ma'lumot berib yuborish uchun extension
fun Fragment.putArguments(block: Bundle.() -> Unit): Fragment {
    val bundle = arguments ?: Bundle()
    block(bundle)
    arguments = bundle
    return this
}

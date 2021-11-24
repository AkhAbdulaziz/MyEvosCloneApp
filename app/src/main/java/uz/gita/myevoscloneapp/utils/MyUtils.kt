package uz.gita.myevoscloneapp.utils

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.viewbinding.ViewBinding
import timber.log.Timber

fun <T : ViewBinding> T.scope(f: T.() -> Unit) {
    f(this)
}

fun timber(message: String, tag: String = "TTT") {
    Timber.tag(tag).d(message)
}

fun makeLog(message: String, tag: String = "TTT") {
    Log.d(tag, message)
}

//hide keyboard from fragment
fun hideKeyboardFrom(context: Context, view: View) {
    val imm: InputMethodManager =
        context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.getWindowToken(), 0)
}

// hide keyboard from activity
fun hideKeyboard(activity: Activity) {
    val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    //Find the currently focused view, so we can grab the correct window token from it.
    var view = activity.currentFocus
    //If no view currently has focus, create a new one, just so we can grab a window token from it
    if (view == null) {
        view = View(activity)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

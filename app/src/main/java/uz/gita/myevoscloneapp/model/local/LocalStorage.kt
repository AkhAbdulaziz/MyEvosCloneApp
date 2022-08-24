package uz.gita.myevoscloneapp.model.local

import com.securepreferences.SecurePreferences
import uz.gita.myevoscloneapp.app.App
import uz.gita.myevoscloneapp.utils.IntPreference
import uz.gita.myevoscloneapp.utils.StringPreference

class LocalStorage() {
    private val KEY = "SHDIJHEUNNSONAIEFIUBOMXOSMB4s5456sd4cv8d"
    private val securePref = SecurePreferences(App.instance, KEY, "local_storage.xml")

    var startScreen: String
        set(value) = securePref.edit().putString("StartScreen", value).apply()
        get() = securePref.getString("StartScreen", "LOGIN")!!

    var selectedFoods: String by StringPreference(securePref)

    var userFavouriteFoods: String by StringPreference(securePref)

    var lastMapType: Int by IntPreference(securePref)
}
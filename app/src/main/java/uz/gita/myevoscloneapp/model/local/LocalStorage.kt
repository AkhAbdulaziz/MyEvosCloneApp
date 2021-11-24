package uz.gita.myevoscloneapp.model.local

import com.securepreferences.SecurePreferences
import uz.gita.myevoscloneapp.app.App
import uz.gita.myevoscloneapp.model.data.FoodData

class LocalStorage() {
    private val KEY = "SHDIJHEUNNSONAIEFIUBOMXOSMB4s5456sd4cv8d"
    private val securePref = SecurePreferences(App.instance, KEY, "local_storage.xml")

    var startScreen: String
        set(value) = securePref.edit().putString("StartScreen", value).apply()
        get() = securePref.getString("StartScreen", "LOGIN")!!

    var selectedFoods: String
        set(value) = securePref.edit().putString("SELECTED_FOODS", value).apply()
        get() = securePref.getString("SELECTED_FOODS", "")!!

    var userFavouriteFoods: String
        set(value) = securePref.edit().putString("FAVOURITE_FOODS", value).apply()
        get() = securePref.getString("FAVOURITE_FOODS", "")!!

}
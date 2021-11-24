package uz.gita.myevoscloneapp.domain.repository.impl

import com.google.firebase.firestore.FirebaseFirestore
import uz.gita.myevoscloneapp.domain.repository.AppRepository
import uz.gita.myevoscloneapp.model.data.AdsData
import uz.gita.myevoscloneapp.model.data.FoodData
import uz.gita.myevoscloneapp.model.enum.StartScreenEnum
import uz.gita.myevoscloneapp.model.local.LocalStorage
import uz.gita.myevoscloneapp.utils.makeLog
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val localStorage: LocalStorage,
    private val fireStore: FirebaseFirestore
) : AppRepository {
    override val adsData = ArrayList<AdsData>()
    override val foodsData = ArrayList<FoodData>()
    override val selectedFoodHashMap = HashMap<Long, Int>()


    override val selectedFoodList: List<FoodData>
        get() {
            val st = localStorage.selectedFoods
            val list = st.split("#")
            val result = ArrayList<FoodData>()
            foodsData.onEach {
                if (list.contains(it.id.toString())) {
                    result.add(it)
                }
            }
            return result
        }

    override val userFavouriteFoodList: List<FoodData>
        get() {
            val st = localStorage.userFavouriteFoods
            val list = st.split("#")
            val result = ArrayList<FoodData>()
            foodsData.onEach {
                if (list.contains(it.id.toString())) {
                    result.add(it)
                }
            }
            return result
        }

    override fun changeUserFavouriteFoodData(foodData: FoodData, boolean: Boolean) {
        val st = localStorage.userFavouriteFoods
        if (boolean) {
            localStorage.userFavouriteFoods = "$st${foodData.id}#"
        } else {
            if (userFavouriteFoodList.contains(foodData)) {
                localStorage.userFavouriteFoods = st.replace("${foodData.id}#", "")
            }
        }
    }

    override fun getUserFavouritesList(): List<FoodData> {
        return userFavouriteFoodList
    }

    override fun clearUserFavouritesList() {
        localStorage.userFavouriteFoods = ""
    }

    override fun getStartScreen(): StartScreenEnum {
        return if (localStorage.startScreen.equals("LOGIN")) {
            StartScreenEnum.LOGIN
        } else {
            StartScreenEnum.MAIN
        }
    }

    override fun setStartScreen(startScreen: StartScreenEnum) {
        localStorage.startScreen = "$startScreen"
    }

    override fun addFood(foodData: FoodData) {
        if (!selectedFoodList.contains(foodData)) {
            val st = localStorage.selectedFoods
            localStorage.selectedFoods = "$st${foodData.id}#"
            selectedFoodHashMap.put(foodData.id, 0)
        }
    }

    override fun removeFood(foodData: FoodData) {
        if (selectedFoodList.contains(foodData)) {
            val st = localStorage.selectedFoods
            localStorage.selectedFoods = st.replace("${foodData.id}#", "")
        }
    }

    private var successLoadListener: (() -> Unit)? = null
    override fun setSuccessLoadListener(block: () -> Unit) {
        successLoadListener = block
    }

    override fun getAds() {
        fireStore.collection("ads")
            .get()
            .addOnSuccessListener { resultList ->
                for (document in resultList) {
                    val id = document["id"] as Long
                    val imageURL = document["imageURL"] as String
                    adsData.add(AdsData(id, imageURL))
                }
                successLoadListener?.invoke()
            }
            .addOnFailureListener { exception ->
                makeLog(exception.message.toString())
            }
    }

    override fun getFoods() {
        fireStore.collection("foods")
            .get()
            .addOnSuccessListener { resultList ->
                for (document in resultList) {
                    document.data.apply {
                        val id = this["id"] as Long
                        val name = this["name"] as String
                        val cost = this["cost"] as Long
                        val imageURL = this["imageURL"] as String
                        val type = this["type"] as Long
                        val isFavourite = this["isFavourite"] as Boolean
                        foodsData.add(FoodData(id, name, cost, imageURL, type, isFavourite))
                    }
                }
                foodsData.sortBy { it.type }
                successLoadListener?.invoke()
            }
            .addOnFailureListener { exception ->
                makeLog(exception.message.toString())
            }
    }
}

package uz.gita.myevoscloneapp.domain.repository.impl

import com.google.firebase.firestore.FirebaseFirestore
import uz.gita.myevoscloneapp.domain.repository.AppRepository
import uz.gita.myevoscloneapp.model.data.AdsData
import uz.gita.myevoscloneapp.model.data.FoodData
import uz.gita.myevoscloneapp.model.data.LocationData
import uz.gita.myevoscloneapp.model.enums.PagesEnum
import uz.gita.myevoscloneapp.model.enums.StartScreenEnum
import uz.gita.myevoscloneapp.model.local.LocalStorage
import uz.gita.myevoscloneapp.utils.timber
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val localStorage: LocalStorage,
    private val fireStore: FirebaseFirestore
) : AppRepository {
    override val adsData = ArrayList<AdsData>()
    override val foodsData = ArrayList<FoodData>()
    override val locationsData = ArrayList<LocationData>()

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
            result.sortBy { it.type }
            return result
        }

    override fun clearSelectedFoodsList() {
        localStorage.selectedFoods = ""
    }

    override fun changeUserFavouriteFoodData(foodData: FoodData, boolean: Boolean) {
        val st = localStorage.userFavouriteFoods
        if (boolean) {
            localStorage.userFavouriteFoods = "$st${foodData.id}#"
        } else {
            localStorage.userFavouriteFoods = st.replace("${foodData.id}#", "")
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

    override fun addFood(foodData: FoodData, count: Int) {
        if (!selectedFoodList.contains(foodData)) {
            val st = localStorage.selectedFoods
            localStorage.selectedFoods = "$st${foodData.id}#"
            selectedFoodHashMap[foodData.id] = count
            orderChangedListener?.invoke()
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

    private var changePageListener: ((PagesEnum) -> Unit)? = null
    override fun setChangePageListener(block: (PagesEnum) -> Unit) {
        changePageListener = block
    }

    private var orderChangedListener: (() -> Unit)? = null
    override fun setOrderChangedListener(block: () -> Unit) {
        orderChangedListener = block
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
                timber(exception.message.toString())
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
                timber(exception.message.toString())
            }
    }

    override fun getLocations() {
        fireStore.collection("locations")
            .get()
            .addOnSuccessListener { resultList ->
                for (document in resultList) {
                    document.data.apply {
                        val id = this["id"] as Long
                        val name = this["name"] as String
                        val description = this["description"] as String
                        val time = this["time"] as String
                        val latitude = this["latitude"] as Double
                        val longitude = this["longitude"] as Double
                        val type = this["type"] as Long
                        locationsData.add(
                            LocationData(id, name, description, time, latitude, longitude, type)
                        )
                    }
                }
                locationsData.sortBy { it.type }
                successLoadListener?.invoke()
            }
            .addOnFailureListener { exception ->
                timber(exception.message.toString())
            }
    }

    override fun changePage(pagesEnum: PagesEnum) {
        changePageListener?.invoke(pagesEnum)
    }
}

package uz.gita.myevoscloneapp.domain.repository

import uz.gita.myevoscloneapp.model.data.AdsData
import uz.gita.myevoscloneapp.model.data.FoodData
import uz.gita.myevoscloneapp.model.data.LocationData
import uz.gita.myevoscloneapp.model.enums.PagesEnum
import uz.gita.myevoscloneapp.model.enums.StartScreenEnum

interface AppRepository {
    val adsData: List<AdsData>
    val foodsData: List<FoodData>
    val locationsData: List<LocationData>
    val selectedFoodList: List<FoodData>
    val userFavouriteFoodList: List<FoodData>

    val selectedFoodHashMap: HashMap<Long, Int>

    fun getStartScreen(): StartScreenEnum
    fun setStartScreen(startScreen: StartScreenEnum)

    fun addFood(foodData: FoodData, count: Int)
    fun removeFood(foodData: FoodData)

    fun setChangePageListener(block: (PagesEnum) -> Unit)
    fun setSuccessLoadListener(block: () -> Unit)
    fun setOrderChangedListener(block: () -> Unit)
    fun getAds()
    fun getFoods()
    fun getLocations()

    fun changeUserFavouriteFoodData(foodData: FoodData, boolean: Boolean)
    fun getUserFavouritesList(): List<FoodData>
    fun clearUserFavouritesList()
    fun clearSelectedFoodsList()

    fun changePage(pagesEnum: PagesEnum)
}
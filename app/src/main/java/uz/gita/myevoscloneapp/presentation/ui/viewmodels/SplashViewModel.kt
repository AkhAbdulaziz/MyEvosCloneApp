package uz.gita.myevoscloneapp.presentation.ui.viewmodels

import androidx.lifecycle.LiveData

interface SplashViewModel {
    val loadAdsLiveData: LiveData<Unit>
    val openMainScreenLiveData : LiveData<Unit>
    val openLoginScreenLiveData : LiveData<Unit>
    fun clearSelectedFoodsList()
}
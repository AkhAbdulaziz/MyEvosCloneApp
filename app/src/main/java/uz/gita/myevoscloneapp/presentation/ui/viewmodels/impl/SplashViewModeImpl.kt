package uz.gita.myevoscloneapp.presentation.ui.viewmodels.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.gita.myevoscloneapp.domain.repository.AppRepository
import uz.gita.myevoscloneapp.model.enum.StartScreenEnum
import uz.gita.myevoscloneapp.presentation.ui.viewmodels.SplashViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModeImpl @Inject constructor(private val appRepository: AppRepository) :
    ViewModel(), SplashViewModel {
    override val loadAdsLiveData = MutableLiveData<Unit>()
    override val openMainScreenLiveData = MutableLiveData<Unit>()
    override val openLoginScreenLiveData = MutableLiveData<Unit>()
    private var count = 0

    init {
        viewModelScope.launch(Dispatchers.IO) {
            appRepository.getAds()
            appRepository.getFoods()
        }

        appRepository.setSuccessLoadListener {
            count++
            if (count == 2) {
                val startScreen = appRepository.getStartScreen()
                if (startScreen == StartScreenEnum.LOGIN) {
                    openLoginScreenLiveData.value = Unit
                    appRepository.setStartScreen(StartScreenEnum.MAIN)
                } else {
                    openMainScreenLiveData.value = Unit
                }
            }
        }
    }
}
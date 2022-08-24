package uz.gita.myevoscloneapp.presentation.ui.viewmodels.impl

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.myevoscloneapp.domain.repository.AppRepository
import uz.gita.myevoscloneapp.model.enums.StartScreenEnum
import uz.gita.myevoscloneapp.presentation.ui.viewmodels.RegisterScreenViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterScreenViewModelImpl @Inject constructor(private val appRepository: AppRepository) :
    ViewModel(), RegisterScreenViewModel {

    override fun setStartScreen(startScreenEnum: StartScreenEnum) {
        appRepository.setStartScreen(startScreenEnum)
    }
}
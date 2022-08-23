package uz.gita.myevoscloneapp.presentation.ui.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.ybq.android.spinkit.SpinKitView
import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.style.FadingCircle
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.myevoscloneapp.R
import uz.gita.myevoscloneapp.databinding.ScreenSplashBinding
import uz.gita.myevoscloneapp.presentation.ui.viewmodels.SplashViewModel
import uz.gita.myevoscloneapp.presentation.ui.viewmodels.impl.SplashViewModeImpl
import uz.gita.myevoscloneapp.utils.gone
import uz.gita.myevoscloneapp.utils.scope
import uz.gita.myevoscloneapp.utils.showToast

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreen : Fragment(R.layout.screen_splash) {
    private val binding by viewBinding(ScreenSplashBinding::bind)
    private val viewModel: SplashViewModel by viewModels<SplashViewModeImpl>()
    private var _progressBar: ProgressBar? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)
        val fadingCircle: Sprite = FadingCircle()
        viewModel.clearSelectedFoodsList()
        _progressBar = progressBar
        (_progressBar as SpinKitView).setIndeterminateDrawable(fadingCircle)

        viewModel.openLoginScreenLiveData.observe(viewLifecycleOwner, openLoginScreenObserver)
        viewModel.openMainScreenLiveData.observe(viewLifecycleOwner, openMainScreenObserver)
    }

    private val openLoginScreenObserver = Observer<Unit> {
        (_progressBar as SpinKitView).gone()
    }

    private val openMainScreenObserver = Observer<Unit> {
        findNavController().navigate(SplashScreenDirections.actionSplashScreenToMainScreen())
        (_progressBar as SpinKitView).gone()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _progressBar = null
    }
}
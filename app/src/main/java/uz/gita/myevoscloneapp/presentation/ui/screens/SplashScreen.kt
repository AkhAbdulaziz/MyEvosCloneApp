package uz.gita.myevoscloneapp.presentation.ui.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.ybq.android.spinkit.SpinKitView
import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.style.FadingCircle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.myevoscloneapp.R
import uz.gita.myevoscloneapp.databinding.ScreenSplashBinding
import uz.gita.myevoscloneapp.utils.scope

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreen : Fragment(R.layout.screen_splash) {
    private val binding by viewBinding(ScreenSplashBinding::bind)
    private var _progressBar: ProgressBar? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)
        val fadingCircle: Sprite = FadingCircle()
        _progressBar = progressBar
        (_progressBar as SpinKitView).setIndeterminateDrawable(fadingCircle)

        lifecycleScope.launch {
            delay(1000)
            findNavController().navigate(SplashScreenDirections.actionSplashScreenToMainScreen())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _progressBar = null
    }
}
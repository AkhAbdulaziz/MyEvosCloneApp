package uz.gita.myevoscloneapp.presentation.ui.screens.intro

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.ybq.android.spinkit.SpinKitView
import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.style.FadingCircle
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.myevoscloneapp.R
import uz.gita.myevoscloneapp.databinding.ScreenContractBinding
import uz.gita.myevoscloneapp.utils.scope

@AndroidEntryPoint
class ContractScreen : Fragment(R.layout.screen_contract) {
    private val binding by viewBinding(ScreenContractBinding::bind)
    private var _progressBar: ProgressBar? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)
        val fadingCircle: Sprite = FadingCircle()
        _progressBar = progressBar
        (_progressBar as SpinKitView).setIndeterminateDrawable(fadingCircle)

        btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        btnNext.setOnClickListener {

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _progressBar = null
    }
}
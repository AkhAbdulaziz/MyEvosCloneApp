package uz.gita.myevoscloneapp.presentation.ui.screens.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.myevoscloneapp.R
import uz.gita.myevoscloneapp.databinding.ScreenAboutUsBinding
import uz.gita.myevoscloneapp.utils.scope

@AndroidEntryPoint
class AboutUsScreen : Fragment(R.layout.screen_about_us) {
    private val binding by viewBinding(ScreenAboutUsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)

        btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

    }
}

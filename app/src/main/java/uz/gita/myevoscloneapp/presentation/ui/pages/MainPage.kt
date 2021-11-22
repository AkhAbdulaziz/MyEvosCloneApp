package uz.gita.myevoscloneapp.presentation.ui.pages

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.myevoscloneapp.R
import uz.gita.myevoscloneapp.databinding.PageMainBinding

class MainPage : Fragment(R.layout.page_main) {
    private val binding by viewBinding(PageMainBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
package uz.gita.myevoscloneapp.presentation.ui.screens.intro

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.myevoscloneapp.R
import uz.gita.myevoscloneapp.databinding.ScreenRegisterBinding
import uz.gita.myevoscloneapp.model.enums.StartScreenEnum
import uz.gita.myevoscloneapp.presentation.ui.viewmodels.RegisterScreenViewModel
import uz.gita.myevoscloneapp.presentation.ui.viewmodels.impl.RegisterScreenViewModelImpl
import uz.gita.myevoscloneapp.utils.disableError
import uz.gita.myevoscloneapp.utils.enableError
import uz.gita.myevoscloneapp.utils.scope

@AndroidEntryPoint
class RegisterScreen : Fragment(R.layout.screen_register) {
    private val binding by viewBinding(ScreenRegisterBinding::bind)
    private val viewModel: RegisterScreenViewModel by viewModels<RegisterScreenViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)

        btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        btnFinish.setOnClickListener {
            viewModel.setStartScreen(StartScreenEnum.MAIN)
            findNavController().navigate(RegisterScreenDirections.actionRegisterScreenToMainScreen())
        }

        phoneNumberEditText.apply {
            addTextChangedListener {
                phoneNumberEditTextLayout.disableError()
                it?.let {
                    btnFinish.isEnabled = it.length == 13 && it.contains("+998")
                }
            }
            setOnFocusChangeListener { view, b ->
                if (!b && (phoneNumberEditText.text.toString()
                        .isEmpty() || phoneNumberEditText.text.toString() == "+998")
                ) {
                    phoneNumberEditTextLayout.enableError()
                    phoneNumberEditTextLayout.error = "Phone is required"
                } else if (!b && !phoneNumberEditText.text.toString().contains("+998")) {
                    phoneNumberEditTextLayout.enableError()
                    phoneNumberEditTextLayout.error = "Need to starts with +998"
                } else if (!b && phoneNumberEditText.text.toString().length != 13) {
                    phoneNumberEditTextLayout.enableError()
                    phoneNumberEditTextLayout.error = "Phone is not valid"
                } else {
                    phoneNumberEditTextLayout.disableError()
                }
            }
        }
    }
}
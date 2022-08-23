package uz.gita.myevoscloneapp.presentation.ui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.myevoscloneapp.R
import uz.gita.myevoscloneapp.databinding.DialogDirectionBinding
import uz.gita.myevoscloneapp.model.data.LocationData
import uz.gita.myevoscloneapp.utils.scope

@AndroidEntryPoint
class DirectionDialog : BottomSheetDialogFragment() {
    private val binding by viewBinding(DialogDirectionBinding::bind)

    private var btnDirectionClickedListener: (() -> Unit)? = null
    fun setBtnDirectionClickedListener(f: () -> Unit) {
        btnDirectionClickedListener = f
    }

    override fun onStart() {
        super.onStart()
        //this forces the sheet to appear at max height even on landscape
        val behavior = BottomSheetBehavior.from(requireView().parent as View)
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.dialog_direction, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)

        btnDirection.setOnClickListener {
            btnDirectionClickedListener?.invoke()
        }
    }
}
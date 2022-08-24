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
import uz.gita.myevoscloneapp.databinding.DialogMapTypeBinding
import uz.gita.myevoscloneapp.model.enums.MapTypes
import uz.gita.myevoscloneapp.utils.scope

@AndroidEntryPoint
class MapTypeDialog(private val mapType: MapTypes) : BottomSheetDialogFragment() {
    private val binding by viewBinding(DialogMapTypeBinding::bind)

    private var btnMapTypeSelectedListener: ((MapTypes) -> Unit)? = null
    fun setMapTypeSelectedListener(f: (MapTypes) -> Unit) {
        btnMapTypeSelectedListener = f
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
    ): View = inflater.inflate(R.layout.dialog_map_type, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
        setLastType(mapType)
        cvNormal.setOnClickListener {
            clearFocusOnVariants()
            it.isSelected = true
            btnMapTypeSelectedListener?.invoke(MapTypes.NORMAL)
        }

        cvTerrain.setOnClickListener {
            clearFocusOnVariants()
            it.isSelected = true
            btnMapTypeSelectedListener?.invoke(MapTypes.TERRAIN)
        }

        cvSatellite.setOnClickListener {
            clearFocusOnVariants()
            it.isSelected = true
            btnMapTypeSelectedListener?.invoke(MapTypes.SATELLITE)
        }

        cvHybrid.setOnClickListener {
            clearFocusOnVariants()
            it.isSelected = true
            btnMapTypeSelectedListener?.invoke(MapTypes.HYBRID)
        }
        btnClose.setOnClickListener {
            dismiss()
        }
    }

    private fun clearFocusOnVariants() = binding.scope {
        if (cvNormal.isSelected) {
            cvNormal.isSelected = false
        } else if (cvTerrain.isSelected) {
            cvTerrain.isSelected = false
        } else if (cvSatellite.isSelected) {
            cvSatellite.isSelected = false
        } else {
            cvHybrid.isSelected = false
        }
    }

    private fun setLastType(mapType: MapTypes) = binding.scope {
        when (mapType) {
            MapTypes.NORMAL -> cvNormal.isSelected = true
            MapTypes.HYBRID -> cvHybrid.isSelected = true
            MapTypes.SATELLITE -> cvSatellite.isSelected = true
            MapTypes.TERRAIN -> cvTerrain.isSelected = true
            MapTypes.NONE -> {
                cvNormal.isSelected = false
                cvTerrain.isSelected = false
                cvSatellite.isSelected = false
                cvHybrid.isSelected = false
            }
        }
    }
}

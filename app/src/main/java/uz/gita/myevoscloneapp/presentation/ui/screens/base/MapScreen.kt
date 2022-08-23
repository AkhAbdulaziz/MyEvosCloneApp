package uz.gita.myevoscloneapp.presentation.ui.screens.base

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.myevoscloneapp.R
import uz.gita.myevoscloneapp.databinding.ScreenMapBinding
import uz.gita.myevoscloneapp.presentation.ui.dialogs.DirectionDialog
import uz.gita.myevoscloneapp.utils.scope
import uz.gita.myevoscloneapp.utils.showToast

@AndroidEntryPoint
class MapScreen : Fragment() {
    private val args: MapScreenArgs by navArgs()
    private val binding by viewBinding(ScreenMapBinding::bind)
    private lateinit var map: GoogleMap
    private lateinit var latLang: LatLng
    private lateinit var directionDialog: DirectionDialog
    private var currentZoomLevel: Float = 10f

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.screen_map, container, false)
        val supportMapFragment =
            childFragmentManager.findFragmentById(R.id.mapLocation) as SupportMapFragment?
        supportMapFragment!!.getMapAsync { googleMap ->
            map = googleMap
            latLang = LatLng(args.locationData.latitude, args.locationData.longitude)
            map.addMarker(
                MarkerOptions().position(latLang).title(args.locationData.name)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location2))
            )
            map.moveCamera(CameraUpdateFactory.newLatLng(latLang))
            map.setOnMarkerClickListener {
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLang, 18f))
                directionDialog = DirectionDialog()
                directionDialog.apply {
                    setBtnDirectionClickedListener {
                        dismiss()
                        startRoute(latLang)
                    }
                }
                directionDialog.show(childFragmentManager, "direction_dialog")
                return@setOnMarkerClickListener true
            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)

        btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        btnZoomIn.setOnClickListener {
            if (currentZoomLevel < map.maxZoomLevel) {
                map.moveCamera(
                    CameraUpdateFactory.newLatLngZoom(
                        latLang,
                        currentZoomLevel + 2f
                    )
                )
                currentZoomLevel += 2f
            }
        }
        btnZoomOut.setOnClickListener {
            if (currentZoomLevel > map.minZoomLevel) {
                map.moveCamera(
                    CameraUpdateFactory.newLatLngZoom(
                        latLang,
                        currentZoomLevel - 2f
                    )
                )
                currentZoomLevel -= 2f
            }
        }
    }

    private fun startRoute(latLng: LatLng) {
        val gmmIntentUri = Uri.parse("google.navigation:q=${latLng.latitude},${latLng.longitude}")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }
}

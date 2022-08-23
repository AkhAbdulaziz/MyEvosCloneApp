package uz.gita.myevoscloneapp.presentation.ui.pages

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ahmadhamwi.tabsync.TabbedListMediator
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.myevoscloneapp.R
import uz.gita.myevoscloneapp.databinding.PageRestaurantsBinding
import uz.gita.myevoscloneapp.model.data.Category
import uz.gita.myevoscloneapp.model.data.LocationData
import uz.gita.myevoscloneapp.model.data.LocationEnum
import uz.gita.myevoscloneapp.presentation.ui.adapters.RestaurantsAdapter
import uz.gita.myevoscloneapp.presentation.ui.adapters.items.LocationItem
import uz.gita.myevoscloneapp.presentation.ui.screens.base.MainScreenDirections
import uz.gita.myevoscloneapp.presentation.ui.viewmodels.RestaurantsViewModel
import uz.gita.myevoscloneapp.presentation.ui.viewmodels.impl.RestaurantsViewModelImpl
import uz.gita.myevoscloneapp.utils.scope

@AndroidEntryPoint
class RestaurantsPage : Fragment(R.layout.page_restaurants) {
    private val binding by viewBinding(PageRestaurantsBinding::bind)
    private val viewModel: RestaurantsViewModel by viewModels<RestaurantsViewModelImpl>()
    private val adapter = RestaurantsAdapter()
    private val listOfTabs = ArrayList<String>()
    private lateinit var categories: MutableList<Category>
    private lateinit var locationsList: List<LocationData>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)
        locationsList = viewModel.getAllLocations()
        adapter.submitList(locationsList)
        locationsRV.adapter = adapter
        locationsRV.layoutManager = LinearLayoutManager(requireContext())

        setTabsTitle()
        setCategories()
        initTabLayout()
        initMediator()

        adapter.setClickListener { i, locationData ->
            findNavController().navigate(
                MainScreenDirections.actionMainScreenToMapScreen(
                    locationData
                )
            )
        }
    }

    private fun setTabsTitle() {
        listOfTabs.add("Locations")
        listOfTabs.add(LocationEnum.TASHKENT.text)
        listOfTabs.add(LocationEnum.FARGONA.text)
        listOfTabs.add(LocationEnum.QASHQADARYO.text)
        listOfTabs.add(LocationEnum.ANDIJON.text)
        listOfTabs.add(LocationEnum.QOQON.text)
        listOfTabs.add(LocationEnum.NAMANGAN.text)
        listOfTabs.add(LocationEnum.SAMARQAND.text)
    }

    private fun setCategories() {
        categories = mutableListOf(
            Category(
                LocationEnum.TASHKENT.text,
                getItemLocationsListByType(LocationEnum.TASHKENT.pos)
            ),
            Category(
                LocationEnum.FARGONA.text,
                getItemLocationsListByType(LocationEnum.FARGONA.pos)
            ),
            Category(
                LocationEnum.QASHQADARYO.text,
                getItemLocationsListByType(LocationEnum.QASHQADARYO.pos)
            ),
            Category(
                LocationEnum.ANDIJON.text,
                getItemLocationsListByType(LocationEnum.ANDIJON.pos)
            ),
            Category(
                LocationEnum.QOQON.text,
                getItemLocationsListByType(LocationEnum.QOQON.pos)
            ),
            Category(
                LocationEnum.NAMANGAN.text,
                getItemLocationsListByType(LocationEnum.NAMANGAN.pos)
            ),
            Category(
                LocationEnum.SAMARQAND.text,
                getItemLocationsListByType(LocationEnum.SAMARQAND.pos)
            )
        )
    }

    private fun initTabLayout() = binding.scope {
        for (category in categories) {
            tabLayout.addTab(tabLayout.newTab().setText(category.name))
        }
    }

    private fun initMediator() = binding.scope {
        TabbedListMediator(
            locationsRV,
            tabLayout,
            categories.indices.toList()
        ).attach()
    }

    private fun getItemLocationsListByType(type: Long): ArrayList<LocationItem> {
        val result = ArrayList<LocationItem>()
        for (locationData in locationsList) {
            if (locationData.type == type) {
                result.add(LocationItem(locationData))
            }
        }
        return result
    }
}
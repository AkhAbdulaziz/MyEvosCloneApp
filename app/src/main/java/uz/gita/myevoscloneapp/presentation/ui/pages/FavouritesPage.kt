package uz.gita.myevoscloneapp.presentation.ui.pages

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.myevoscloneapp.R
import uz.gita.myevoscloneapp.databinding.PageFavouritesBinding
import uz.gita.myevoscloneapp.model.data.FoodData
import uz.gita.myevoscloneapp.presentation.ui.adapters.FavouritesPageAdapter
import uz.gita.myevoscloneapp.presentation.ui.screens.base.MainScreenDirections
import uz.gita.myevoscloneapp.presentation.ui.viewmodels.FavouritePageViewModel
import uz.gita.myevoscloneapp.presentation.ui.viewmodels.impl.FavouritePageViewModelImpl
import uz.gita.myevoscloneapp.utils.gone
import uz.gita.myevoscloneapp.utils.scope
import uz.gita.myevoscloneapp.utils.visible

@AndroidEntryPoint
class FavouritesPage : Fragment(R.layout.page_favourites) {
    private val binding by viewBinding(PageFavouritesBinding::bind)
    private val viewModel: FavouritePageViewModel by viewModels<FavouritePageViewModelImpl>()
    private val favouritesPageAdapter = FavouritesPageAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)
        loadData()
        favouritesRV.adapter = favouritesPageAdapter
        favouritesRV.layoutManager = LinearLayoutManager(requireContext())
        favouritesPageAdapter.setClickListener { pos, foodData ->
            findNavController().navigate(
                MainScreenDirections.actionMainScreenToFoodInfoScreen(
                    foodData
                )
            )
        }

        favouritesPageAdapter.setCountChangedListener { foodData, count ->
            viewModel.addFood(foodData, count)
        }

        btnClearList.setOnClickListener {
            viewModel.clearUserFavouritesList()
            loadData()
        }
    }

    private fun loadData() {
        val list = viewModel.getUserFavouritesList()
        favouritesPageAdapter.submitList(list)
        checkEmpty(list)
    }

    private fun checkEmpty(list: List<FoodData>) = binding.scope {
        if (list.isEmpty()) {
            textEmptyList.visible()
            btnClearList.gone()
        } else {
            textEmptyList.gone()
            btnClearList.visible()
        }
    }
}

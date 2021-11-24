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

        btnClearList.setOnClickListener {
            for (data in viewModel.getAllPopularFoods()) {
                data.isFavourite = false
            }
            viewModel.clearUserFavouritesList()
            loadData()
        }
    }

    private fun loadData() {
        favouritesPageAdapter.submitList(viewModel.getUserFavouritesList())
        checkEmpty()
    }

    private fun checkEmpty() = binding.scope {
        textEmptyList.apply {
            if (favouritesPageAdapter.itemCount == 0) {
                visible()
            } else {
                gone()
            }
        }
    }
}
package uz.gita.myevoscloneapp.presentation.ui.pages

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.myevoscloneapp.R
import uz.gita.myevoscloneapp.databinding.PageMainBinding
import uz.gita.myevoscloneapp.model.data.AdsData
import uz.gita.myevoscloneapp.presentation.ui.adapters.AdsAdapter
import uz.gita.myevoscloneapp.presentation.ui.adapters.PopularFoodsAdapter
import uz.gita.myevoscloneapp.presentation.ui.screens.base.MainScreenDirections
import uz.gita.myevoscloneapp.presentation.ui.viewmodels.MainPageViewModel
import uz.gita.myevoscloneapp.presentation.ui.viewmodels.impl.MainPageViewModelImpl
import uz.gita.myevoscloneapp.utils.scope
import uz.gita.myevoscloneapp.utils.showToast
import uz.gita.myevoscloneapp.utils.timber

@AndroidEntryPoint
class MainPage : Fragment(R.layout.page_main) {
    private val binding by viewBinding(PageMainBinding::bind)
    private val viewModel: MainPageViewModel by viewModels<MainPageViewModelImpl>()
    private lateinit var adsAdapter: AdsAdapter
    private val popFoodAdapter = PopularFoodsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)
        val adsList = ArrayList<AdsData>()
        adsList.addAll(viewModel.getAllAds())
        popFoodAdapter.submitList(viewModel.getAllPopularFoods())

        adsAdapter = AdsAdapter(requireActivity(), adsList)
        adsPager.adapter = adsAdapter

        popularFoodsRV.adapter = popFoodAdapter
        popularFoodsRV.isNestedScrollingEnabled = false
        popularFoodsRV.layoutManager = GridLayoutManager(requireContext(), 2)

        popFoodAdapter.setClickListener { pos, foodData ->
            findNavController().navigate(
                MainScreenDirections.actionMainScreenToFoodInfoScreen(
                    foodData
                )
            )
        }

        popFoodAdapter.setCountChangedListener { foodData, count ->
            viewModel.addFood(foodData, count)
        }

        TabLayoutMediator(tabLayout, adsPager) { tab, pos ->

        }.attach()

        lifecycleScope.launch(Dispatchers.Main) {
            while (true) {
                delay(2000L)
                if (adsPager.currentItem == adsList.size - 1) {
                    adsPager.setCurrentItem(0, false)
                } else {
                    adsPager.currentItem += 1
                }
            }
        }

        layoutEvosFamily.setOnClickListener {
            showToast("EVOS Oilasi")
        }

        txtBtnDiscounts.setOnClickListener {
            showToast("Aksiyalar")
        }
    }
}
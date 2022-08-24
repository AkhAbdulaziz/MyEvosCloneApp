package uz.gita.myevoscloneapp.presentation.ui.screens.base

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.myevoscloneapp.R
import uz.gita.myevoscloneapp.databinding.ScreenMainNavBinding
import uz.gita.myevoscloneapp.model.data.FoodData
import uz.gita.myevoscloneapp.model.enums.PagesEnum
import uz.gita.myevoscloneapp.presentation.ui.adapters.MainScreenAdapter
import uz.gita.myevoscloneapp.presentation.ui.viewmodels.MainScreenViewModel
import uz.gita.myevoscloneapp.presentation.ui.viewmodels.impl.MainScreenViewModelImpl
import uz.gita.myevoscloneapp.utils.scope

@AndroidEntryPoint
class MainScreen : Fragment(R.layout.screen_main_nav),
    NavigationView.OnNavigationItemSelectedListener {
    private val binding by viewBinding(ScreenMainNavBinding::bind)
    private val viewModel: MainScreenViewModel by viewModels<MainScreenViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getSelectedFoods()
        val adapter = MainScreenAdapter(childFragmentManager, lifecycle)

        innerLayout.pager.adapter = adapter
        innerLayout.pager.isUserInputEnabled = false

        innerLayout.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.main -> innerLayout.pager.setCurrentItem(0, false)
                R.id.favourite -> innerLayout.pager.setCurrentItem(1, false)
                R.id.food_menu -> innerLayout.pager.setCurrentItem(2, false)
                R.id.restaurants -> innerLayout.pager.setCurrentItem(3, false)
                else -> innerLayout.pager.setCurrentItem(4, false)
            }
            return@setOnItemSelectedListener true
        }
        adapter.setCountChangedListener {
            viewModel.getSelectedFoods()
        }

        innerLayout.btnMenu.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        btnBackToScreen.setOnClickListener {
            drawerLayout.closeDrawer(GravityCompat.START)
        }

        innerLayout.btnBasket.setOnClickListener {
            findNavController().navigate(MainScreenDirections.actionMainScreenToBasketScreen())
        }

        lineAbout.setOnClickListener {
            findNavController().navigate(MainScreenDirections.actionMainScreenToAboutUsScreen())
        }

        viewModel.changePageLiveData.observe(viewLifecycleOwner, changePageObserver)
        viewModel.selectedFoodsLiveData.observe(viewLifecycleOwner, selectedFoodsObserver)
        /*viewModel.setSelectedFoodsListener {
            var allOrdersCount = 0
            for (food: FoodData in it) {
                allOrdersCount += food.count
            }
            binding.innerLayout.txtOrdersCount.text = "$allOrdersCount"
        }*/
    }

    private val selectedFoodsObserver = Observer<List<FoodData>> {
        var allOrdersCount = 0
        for (food: FoodData in it) {
            allOrdersCount += food.count
        }
        binding.innerLayout.txtOrdersCount.text = "$allOrdersCount"
    }

    private val changePageObserver = Observer<PagesEnum> { pagesEnum ->
//        binding.innerLayout.pager.setCurrentItem(pagesEnum.getPageIndex(), false)
        binding.innerLayout.bottomNavigationView.selectedItemId = pagesEnum.getId()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean = true

    override fun onDestroy() {
        super.onDestroy()
        viewModel.clearSelectedFoodsList()
    }
}

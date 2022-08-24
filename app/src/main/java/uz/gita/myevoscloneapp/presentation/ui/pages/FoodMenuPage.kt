package uz.gita.myevoscloneapp.presentation.ui.pages

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ahmadhamwi.tabsync.TabbedListMediator
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.myevoscloneapp.R
import uz.gita.myevoscloneapp.databinding.PageFoodMenuBinding
import uz.gita.myevoscloneapp.model.data.Category
import uz.gita.myevoscloneapp.model.data.FoodData
import uz.gita.myevoscloneapp.model.data.TypeEnum
import uz.gita.myevoscloneapp.presentation.ui.adapters.FoodMenuAdapter
import uz.gita.myevoscloneapp.presentation.ui.adapters.items.ItemFoodMenu
import uz.gita.myevoscloneapp.presentation.ui.screens.base.MainScreenDirections
import uz.gita.myevoscloneapp.presentation.ui.viewmodels.FoodMenuViewModel
import uz.gita.myevoscloneapp.presentation.ui.viewmodels.impl.FoodMenuViewModelImpl
import uz.gita.myevoscloneapp.utils.scope

@AndroidEntryPoint
class FoodMenuPage : Fragment(R.layout.page_food_menu) {
    private val binding by viewBinding(PageFoodMenuBinding::bind)
    private val viewModel: FoodMenuViewModel by viewModels<FoodMenuViewModelImpl>()
    private val adapter = FoodMenuAdapter()
    private val listOfTabs = ArrayList<String>()
    private var foodsList = ArrayList<FoodData>()
    private lateinit var categories: MutableList<Category>

    private var countChangedListener: (() -> Unit)? = null
    fun setCountChangedListener(block: () -> Unit) {
        countChangedListener = block
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllFoods()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)
        foodMenuRV.adapter = adapter
        foodMenuRV.layoutManager = GridLayoutManager(requireContext(), 2)

        setTabsTitle()
        setCategories()
        initTabLayout()
        initMediator()

        adapter.setClickListener { pos, foodData ->
            findNavController().navigate(
                MainScreenDirections.actionMainScreenToFoodInfoScreen(
                    foodData
                )
            )
        }

        adapter.setCountChangedListener { foodData, count ->
            countChangedListener?.invoke()
            viewModel.addFood(foodData, count)
        }

        viewModel.allFoodsLiveData.observe(viewLifecycleOwner, allFoodsObserver)
    }

    private val allFoodsObserver = Observer<List<FoodData>> {
        foodsList.clear()
        foodsList.addAll(it)
        adapter.submitList(foodsList)
        adapter.notifyDataSetChanged()
    }

    private fun setTabsTitle() {
        listOfTabs.add("Foods")
        listOfTabs.add(TypeEnum.SET.text)
        listOfTabs.add(TypeEnum.LAVASH.text)
        listOfTabs.add(TypeEnum.SHAURMA.text)
        listOfTabs.add(TypeEnum.DONAR.text)
        listOfTabs.add(TypeEnum.BURGER.text)
        listOfTabs.add(TypeEnum.XOTDOG.text)
        listOfTabs.add(TypeEnum.DESERTLAR.text)
        listOfTabs.add(TypeEnum.ICHIMLIKLAR.text)
        listOfTabs.add(TypeEnum.GAZAKLAR.text)
    }

    private fun setCategories() {
        categories = mutableListOf(
            Category(
                TypeEnum.SET.text,
                getItemFoodsListByType(TypeEnum.SET.pos)
            ),
            Category(
                TypeEnum.LAVASH.text,
                getItemFoodsListByType(TypeEnum.LAVASH.pos)
            ),
            Category(
                TypeEnum.SHAURMA.text,
                getItemFoodsListByType(TypeEnum.SHAURMA.pos)
            ),
            Category(
                TypeEnum.DONAR.text,
                getItemFoodsListByType(TypeEnum.DONAR.pos)
            ),
            Category(
                TypeEnum.BURGER.text,
                getItemFoodsListByType(TypeEnum.BURGER.pos)
            ),
            Category(
                TypeEnum.XOTDOG.text,
                getItemFoodsListByType(TypeEnum.XOTDOG.pos)
            ),
            Category(
                TypeEnum.DESERTLAR.text,
                getItemFoodsListByType(TypeEnum.DESERTLAR.pos)
            ),
            Category(
                TypeEnum.ICHIMLIKLAR.text,
                getItemFoodsListByType(TypeEnum.ICHIMLIKLAR.pos)
            ),
            Category(
                TypeEnum.GAZAKLAR.text,
                getItemFoodsListByType(TypeEnum.GAZAKLAR.pos)
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
            foodMenuRV,
            tabLayout,
            categories.indices.toList()
        ).attach()
    }

    private fun getItemFoodsListByType(type: Long): ArrayList<ItemFoodMenu> {
        val result = ArrayList<ItemFoodMenu>()
        for (foodData in foodsList) {
            if (foodData.type == type) {
                result.add(ItemFoodMenu(foodData))
            }
        }
        return result
    }
}
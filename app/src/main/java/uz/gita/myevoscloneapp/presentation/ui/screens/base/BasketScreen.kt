package uz.gita.myevoscloneapp.presentation.ui.screens.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.myevoscloneapp.R
import uz.gita.myevoscloneapp.databinding.ScreenBasketBinding
import uz.gita.myevoscloneapp.model.data.FoodData
import uz.gita.myevoscloneapp.model.enums.PagesEnum
import uz.gita.myevoscloneapp.presentation.ui.adapters.BasketScreenAdapter
import uz.gita.myevoscloneapp.presentation.ui.viewmodels.BasketViewModel
import uz.gita.myevoscloneapp.presentation.ui.viewmodels.impl.BasketViewModelImpl
import uz.gita.myevoscloneapp.utils.gone
import uz.gita.myevoscloneapp.utils.scope
import uz.gita.myevoscloneapp.utils.showToast
import uz.gita.myevoscloneapp.utils.visible

@AndroidEntryPoint
class BasketScreen : Fragment(R.layout.screen_basket) {
    private val binding by viewBinding(ScreenBasketBinding::bind)
    private val viewModel: BasketViewModel by viewModels<BasketViewModelImpl>()
    private val basketScreenAdapter = BasketScreenAdapter()
    private var allOrdersPrice: Long = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)
        viewModel.selectedFoodsLiveData.observe(viewLifecycleOwner, selectedFoodsObserver)
        viewModel.getSelectedFoods()

        orderedFoodsRV.adapter = basketScreenAdapter
        orderedFoodsRV.layoutManager = LinearLayoutManager(requireContext())

        basketScreenAdapter.setCountChangedListener { foodData, count ->
            viewModel.addFood(foodData, count)
        }

        btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        btnOrder.setOnClickListener {
            showToast("Buyurtma jo'natildi")
        }

        btnSwitchToMenu.setOnClickListener {
            viewModel.changePage(PagesEnum.MENU)
            findNavController().popBackStack()
        }
    }

    private val selectedFoodsObserver = Observer<List<FoodData>> {
        binding.apply {
            if (it.isEmpty()) {
                imgEmptyBg.visible()
                imgEmptyBasket.visible()
                img0EmptyBasket.visible()
                txtEmptyBasket.visible()
                txtEmptyBasket2.visible()
                btnSwitchToMenu.visible()

                btnOrder.gone()
            } else {
                basketScreenAdapter.submitList(it)
                imgEmptyBg.gone()
                imgEmptyBasket.gone()
                img0EmptyBasket.gone()
                txtEmptyBasket.gone()
                txtEmptyBasket2.gone()
                btnSwitchToMenu.gone()

                btnOrder.visible()

                for (food: FoodData in it) {
                    allOrdersPrice += food.cost
                }

                if (allOrdersPrice != 0L) {
                    txtOrderPrice.text = "$allOrdersPrice so'm"
                    txtDeliveryPrice.text = "6000 so'm"
                    txtTotalPrice.text = "${allOrdersPrice + 6000} so'm"
                }
            }
        }
    }
}

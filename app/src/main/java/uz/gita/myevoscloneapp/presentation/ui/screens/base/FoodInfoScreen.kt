package uz.gita.myevoscloneapp.presentation.ui.screens.base

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.myevoscloneapp.R
import uz.gita.myevoscloneapp.databinding.ScreenFoodInfoBinding
import uz.gita.myevoscloneapp.model.data.FoodData
import uz.gita.myevoscloneapp.presentation.ui.viewmodels.FoodInfoViewModel
import uz.gita.myevoscloneapp.presentation.ui.viewmodels.impl.FoodInfoViewModelImpl
import uz.gita.myevoscloneapp.utils.gone
import uz.gita.myevoscloneapp.utils.scope
import uz.gita.myevoscloneapp.utils.visible

@AndroidEntryPoint
class FoodInfoScreen : Fragment(R.layout.screen_food_info) {
    private val binding by viewBinding(ScreenFoodInfoBinding::bind)
    private val viewModel: FoodInfoViewModel by viewModels<FoodInfoViewModelImpl>()
    private val args: FoodInfoScreenArgs by navArgs()
    private lateinit var foodData: FoodData

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)
        foodData = args.foodData
        setInformation()
        checkFavourite()
        viewModel.getSelectedFoods()

        btnBackToScreen.setOnClickListener {
            findNavController().popBackStack()
        }

        btnBasket.setOnClickListener {
            findNavController().navigate(FoodInfoScreenDirections.actionFoodInfoScreenToBasketScreen())
        }

        btnFavourite.setOnClickListener {
            if (viewModel.getUserFavouritesList().contains(foodData)) {
                heartStatus.setImageResource(R.drawable.ic_heart)
                layoutFavourite.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.color_white
                    )
                )
                txtFavourite.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.color_black
                    )
                )
                viewModel.changeUserFavouriteFoodData(foodData, false)
            } else {
                heartStatus.setImageResource(R.drawable.ic_heart_white)
                layoutFavourite.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.color_base
                    )
                )
                txtFavourite.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.white
                    )
                )
                viewModel.changeUserFavouriteFoodData(foodData, true)
            }
        }

        btnAddFood.setOnClickListener {
            it.gone()
            txtCount.text = "${foodData.count + 1}x"
            foodData.count += 1
            layoutIncDec.visible()
        }
        btnIncrement.setOnClickListener {
            txtCount.text = "${foodData.count + 1}x"
            foodData.count += 1
            /*if (foodData.count > 0) {
                btnAddFood.gone()
                layoutIncDec.visible()
            }*/
        }
        btnDecrement.setOnClickListener {
            txtCount.text = "${foodData.count - 1}x"
            foodData.count -= 1
            if (foodData.count <= 0) {
                btnAddFood.visible()
                layoutIncDec.gone()
            }
        }
        viewModel.selectedFoodsLiveData.observe(viewLifecycleOwner, selectedFoodsObserver)
    }

    private val selectedFoodsObserver = Observer<List<FoodData>> {
        var allOrdersCount = 0
        for (food: FoodData in it) {
            allOrdersCount += food.count
        }
        binding.txtOrdersCount.text = "$allOrdersCount"
    }

    private fun checkFavourite() = binding.scope {
        if (!viewModel.getUserFavouritesList().contains(foodData)) {
            heartStatus.setImageResource(R.drawable.ic_heart)
            layoutFavourite.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.color_white
                )
            )
            txtFavourite.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.color_black
                )
            )
        } else {
            heartStatus.setImageResource(R.drawable.ic_heart_white)
            layoutFavourite.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.color_base
                )
            )
            txtFavourite.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
        }
    }

    private fun setInformation() = binding.scope {

        Glide.with(imgFoodInfo.context)
            .load(foodData.imageURL)
            .placeholder(R.drawable.ic_place_holder)
            .error(R.drawable.ic_error)
            .into(imgFoodInfo)

        txtCount.text = "${foodData.count}x"

        btnAddFood.visible(foodData.count <= 0)
        layoutIncDec.visible(foodData.count > 0)

        nameFoodInfo.text = foodData.name
        priceFoodInfo.text = "${foodData.cost} so'm"

    }
}

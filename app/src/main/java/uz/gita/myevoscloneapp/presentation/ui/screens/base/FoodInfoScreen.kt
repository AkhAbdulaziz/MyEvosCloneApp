package uz.gita.myevoscloneapp.presentation.ui.screens.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.myevoscloneapp.R
import uz.gita.myevoscloneapp.databinding.ScreenFoodInfoBinding
import uz.gita.myevoscloneapp.presentation.ui.viewmodels.FoodInfoViewModel
import uz.gita.myevoscloneapp.presentation.ui.viewmodels.impl.FoodInfoViewModelImpl
import uz.gita.myevoscloneapp.utils.scope
import uz.gita.myevoscloneapp.utils.showToast

@AndroidEntryPoint
class FoodInfoScreen : Fragment(R.layout.screen_food_info) {
    private val binding by viewBinding(ScreenFoodInfoBinding::bind)
    private val viewModel: FoodInfoViewModel by viewModels<FoodInfoViewModelImpl>()
    private val args: FoodInfoScreenArgs by navArgs()
    private val foodData = args.foodData
    private var isFavourite = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)
        setInformation()
        isFavourite = if (viewModel.getUserFavouritesList().contains(foodData)) {
            val index = viewModel.getUserFavouritesList().indexOf(foodData)
            viewModel.getUserFavouritesList()[index].isFavourite
        } else {
            false
        }

        btnBackToScreen.setOnClickListener {
            findNavController().popBackStack()
        }

        btnBasket.setOnClickListener {
            showToast("Basket clicked on info screen")
        }

        btnFavourite.setOnClickListener {
            if (isFavourite) {
                heartStatus.setImageResource(R.drawable.ic_heart)
                viewModel.changeUserFavouriteFoodData(foodData, false)
            } else {
                heartStatus.setImageResource(R.drawable.heart_red)
                viewModel.changeUserFavouriteFoodData(foodData, true)
            }
        }

        btnAddFood.setOnClickListener {
            txtCount.text = "${foodData.count + 1}x"
            foodData.count += 1
        }
        btnIncrement.setOnClickListener {
            txtCount.text = "${foodData.count + 1}x"
            foodData.count += 1
        }
        btnDecrement.setOnClickListener {
            txtCount.text = "${foodData.count - 1}x"
            foodData.count -= 1
        }
    }

    private fun setInformation() = binding.scope {

        Glide.with(imgFoodInfo.context)
            .load(foodData.imageURL)
            .placeholder(R.drawable.ic_place_holder)
            .error(R.drawable.ic_error)
            .into(imgFoodInfo)

        nameFoodInfo.text = foodData.name
        priceFoodInfo.text = "${foodData.cost} so'm"
    }
}
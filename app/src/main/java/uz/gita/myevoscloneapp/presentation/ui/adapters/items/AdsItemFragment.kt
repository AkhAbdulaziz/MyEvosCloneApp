package uz.gita.myevoscloneapp.presentation.ui.adapters.items

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import uz.gita.myevoscloneapp.R
import uz.gita.myevoscloneapp.databinding.ItemAdsBinding
import uz.gita.myevoscloneapp.utils.scope

class AdsItemFragment : Fragment(R.layout.item_ads) {
    private val binding by viewBinding(ItemAdsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)
        val bundle = requireArguments()

        Glide.with(imgAds.context)
            .load(bundle.getString("ADS_IMAGE_URL"))
            .placeholder(R.drawable.ic_place_holder)
            .error(R.drawable.ic_error)
            .into(imgAds)
    }
}
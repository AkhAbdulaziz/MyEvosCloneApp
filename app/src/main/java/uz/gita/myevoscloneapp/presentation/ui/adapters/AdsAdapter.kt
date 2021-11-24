package uz.gita.myevoscloneapp.presentation.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.gita.myevoscloneapp.model.data.AdsData
import uz.gita.myevoscloneapp.presentation.ui.adapters.items.AdsItemFragment
import uz.gita.myevoscloneapp.utils.putArguments

class AdsAdapter(
    fragmentActivity: FragmentActivity,
    private val list: List<AdsData>
) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = list.size
    override fun createFragment(position: Int): Fragment =
        AdsItemFragment().putArguments { putString("ADS_IMAGE_URL", list[position].imageURL) }
}
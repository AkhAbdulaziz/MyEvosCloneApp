package uz.gita.myevoscloneapp.presentation.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.gita.myevoscloneapp.presentation.ui.pages.*

class MainScreenAdapter(fm: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fm, lifecycle) {

    private var clickHomeButtonListener: (() -> Unit)? = null
    fun setOnClickHomeButtonListener(block: () -> Unit) {
        clickHomeButtonListener = block
    }

    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MainPage().apply {
                setOnClickHomeButtonListener {
                    clickHomeButtonListener?.invoke()
                }
            }
            1 -> FavouritesPage()
            2 -> FoodMenuPage()
            3 -> RestaurantsPage()
            else -> ProfilePage()
        }
    }
}
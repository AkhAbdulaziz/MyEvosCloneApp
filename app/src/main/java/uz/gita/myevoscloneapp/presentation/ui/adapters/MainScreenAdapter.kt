package uz.gita.myevoscloneapp.presentation.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.gita.myevoscloneapp.presentation.ui.pages.*

class MainScreenAdapter(fm: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fm, lifecycle) {

    private var countChangedListener: (() -> Unit)? = null
    fun setCountChangedListener(block: () -> Unit) {
        countChangedListener = block
    }

    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MainPage().apply {
                setCountChangedListener {
                    countChangedListener?.invoke()
                }
            }
            1 -> FavouritesPage().apply {
                setCountChangedListener {
                    countChangedListener?.invoke()
                }
            }
            2 -> FoodMenuPage().apply {
                setCountChangedListener {
                    countChangedListener?.invoke()
                }
            }
            3 -> RestaurantsPage()
            else -> ProfilePage()
        }
    }
}
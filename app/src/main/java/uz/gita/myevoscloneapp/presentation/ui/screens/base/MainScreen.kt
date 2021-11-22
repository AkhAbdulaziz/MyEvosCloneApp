package uz.gita.myevoscloneapp.presentation.ui.screens.base

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.myevoscloneapp.R
import uz.gita.myevoscloneapp.databinding.ScreenMainNavBinding
import uz.gita.myevoscloneapp.presentation.ui.adapters.MainScreenAdapter
import uz.gita.myevoscloneapp.utils.scope

@AndroidEntryPoint
class MainScreen : Fragment(R.layout.screen_main_nav),
    NavigationView.OnNavigationItemSelectedListener {
    private val binding by viewBinding(ScreenMainNavBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)
        val adapter = MainScreenAdapter(childFragmentManager, lifecycle)

        innerLayout.pager.adapter = adapter
        innerLayout.pager.isUserInputEnabled = false

        innerLayout.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.main -> innerLayout.pager.currentItem = 0
                R.id.favourite -> innerLayout.pager.currentItem = 1
                R.id.food_menu -> innerLayout.pager.currentItem = 2
                R.id.restaurants -> innerLayout.pager.currentItem = 3
                else -> innerLayout.pager.currentItem = 4
            }
            return@setOnItemSelectedListener true
        }
        adapter.setOnClickHomeButtonListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        btnBackToScreen.setOnClickListener {
            drawerLayout.closeDrawer(GravityCompat.START)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean = true
}
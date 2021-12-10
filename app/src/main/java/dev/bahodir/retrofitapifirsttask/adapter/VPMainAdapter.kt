package dev.bahodir.retrofitapifirsttask.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import dev.bahodir.retrofitapifirsttask.fragment.main.HistoryFragment
import dev.bahodir.retrofitapifirsttask.fragment.main.HomeFragment
import dev.bahodir.retrofitapifirsttask.fragment.main.SearchFragment
import dev.bahodir.retrofitapifirsttask.fragment.main.SelectedFragment

class VPMainAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> SearchFragment()
            2 -> SelectedFragment()
            else -> HistoryFragment()
        }
    }
}
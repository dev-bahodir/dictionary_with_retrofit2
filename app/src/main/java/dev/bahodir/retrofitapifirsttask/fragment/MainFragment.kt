package dev.bahodir.retrofitapifirsttask.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import dev.bahodir.retrofitapifirsttask.R
import dev.bahodir.retrofitapifirsttask.adapter.VPMainAdapter
import dev.bahodir.retrofitapifirsttask.databinding.FragmentMainBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var vpMainAdapter: VPMainAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        vpMainAdapter = VPMainAdapter(requireActivity())
        binding.vpMain.adapter = vpMainAdapter

        binding.vpMain.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        binding.bottomNavMenu.selectedItemId = R.id.home
                    }
                    1 -> {
                        binding.bottomNavMenu.selectedItemId = R.id.search
                    }
                    2 -> {
                        binding.bottomNavMenu.selectedItemId = R.id.selected
                    }
                    else -> {
                        binding.bottomNavMenu.selectedItemId = R.id.history
                    }
                }
            }
        })

        binding.bottomNavMenu.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> binding.vpMain.currentItem = 0
                R.id.search -> binding.vpMain.currentItem = 1
                R.id.selected -> binding.vpMain.currentItem = 2
                R.id.history -> binding.vpMain.currentItem = 3
            }
            true
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
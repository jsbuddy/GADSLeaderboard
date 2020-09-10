package com.example.gadsleaderboard.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.gadsleaderboard.R
import com.example.gadsleaderboard.adapters.SectionsPagerAdapter
import com.example.gadsleaderboard.databinding.FragmentHomeBinding
import com.example.gadsleaderboard.ui.main.MainActivity
import com.example.gadsleaderboard.ui.main.MainViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var model: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        model = (activity as MainActivity).model

        setupToolbar()
        setupViewPager()

        return binding.root
    }

    private fun setupToolbar() {
        binding.toolbar.inflateMenu(R.menu.menu_home)
        binding.toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_submit -> {
                    findNavController().navigate(R.id.action_homeFragment_to_submitFragment)
                    true
                }
                else -> false
            }
        }
    }

    private fun setupViewPager() {
        val sectionsPagerAdapter = SectionsPagerAdapter(requireContext(), childFragmentManager)
        binding.viewPager.adapter = sectionsPagerAdapter
        val tabs = binding.tabs
        tabs.setupWithViewPager(binding.viewPager)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}

package com.example.gadsleaderboard.ui.skilliq

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gadsleaderboard.adapters.SkillIQLeaderRecyclerAdapter
import com.example.gadsleaderboard.databinding.FragmentSkillIQBinding
import com.example.gadsleaderboard.ui.main.MainActivity
import com.example.gadsleaderboard.ui.main.MainViewModel
import com.example.gadsleaderboard.utils.Resource

class SkillIQFragment : Fragment() {

    private var _binding: FragmentSkillIQBinding? = null
    private val binding get() = _binding!!

    private lateinit var leadersAdapter: SkillIQLeaderRecyclerAdapter
    private lateinit var model: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSkillIQBinding.inflate(inflater, container, false)
        model = (activity as MainActivity).model

        setupRecyclerView()

        model.skillIQLeaders.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Loading -> {
                    binding.loading.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.loading.visibility = View.INVISIBLE
                    it.data?.let { response ->
                        leadersAdapter.differ.submitList(response)
                        binding.rvList.visibility = View.VISIBLE
                    }
                }
                is Resource.Error -> {
                    binding.loading.visibility = View.INVISIBLE
                    it.message?.let { message ->
                        Log.e("NetworkError", message)
                    }
                }
            }
        })

        return binding.root
    }

    private fun setupRecyclerView() {
        leadersAdapter = SkillIQLeaderRecyclerAdapter()
        binding.rvList.apply {
            adapter = leadersAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
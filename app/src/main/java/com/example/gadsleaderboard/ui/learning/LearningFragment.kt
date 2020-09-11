package com.example.gadsleaderboard.ui.learning

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gadsleaderboard.adapters.LearningLeaderRecyclerAdapter
import com.example.gadsleaderboard.databinding.FragmentLearningBinding
import com.example.gadsleaderboard.ui.main.MainActivity
import com.example.gadsleaderboard.ui.main.MainViewModel
import com.example.gadsleaderboard.utils.Resource

class LearningFragment : Fragment() {

    private var _binding: FragmentLearningBinding? = null
    private val binding get() = _binding!!

    private lateinit var leadersAdapter: LearningLeaderRecyclerAdapter
    private lateinit var model: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLearningBinding.inflate(inflater, container, false)
        model = (activity as MainActivity).model

        setupRecyclerView()

        model.learningLeaders.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Loading -> {
                    binding.loading.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.loading.visibility = View.INVISIBLE
                    it.data?.let { response ->
                        leadersAdapter.differ.submitList(
                            response.sortedWith(compareBy { l -> l.hours }).reversed()
                        )
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
        leadersAdapter = LearningLeaderRecyclerAdapter()
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
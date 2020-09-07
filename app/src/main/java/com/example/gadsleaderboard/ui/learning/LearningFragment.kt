package com.example.gadsleaderboard.ui.learning

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gadsleaderboard.R
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
                    binding.pbCircular.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.pbCircular.visibility = View.INVISIBLE
                    it.data?.let { response ->
                        leadersAdapter.differ.submitList(response)
                        binding.rvList.visibility = View.VISIBLE
                    }
                }
                is Resource.Error -> {
                    binding.pbCircular.visibility = View.INVISIBLE
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
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = LearningFragment()
    }
}
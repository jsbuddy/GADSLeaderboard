package com.example.gadsleaderboard.ui.submit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.gadsleaderboard.R
import com.example.gadsleaderboard.databinding.FragmentSubmitBinding
import com.example.gadsleaderboard.ui.main.MainActivity
import com.example.gadsleaderboard.ui.main.MainViewModel
import com.example.gadsleaderboard.utils.CommonDialog
import com.example.gadsleaderboard.utils.Resource

class SubmitFragment : Fragment() {

    private var _binding: FragmentSubmitBinding? = null
    private val binding get() = _binding!!

    private lateinit var model: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSubmitBinding.inflate(inflater, container, false)
        model = (activity as MainActivity).model

        setupToolbar()
        initialize()

        return binding.root
    }

    private fun setupToolbar() {
        binding.toolbar.title = "Project Submission"
        binding.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        binding.toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
    }

    private fun initialize() {
        binding.layout.setOnFocusChangeListener { view, b ->
            if (b) view.hideKeyboard()
        }

        binding.btnRegister.setOnClickListener { view ->
            view.hideKeyboard()
            if (!validate()) return@setOnClickListener

            CommonDialog(requireContext()).showConfirmation("Are you sure you want to submit?") {
                val firstName = binding.inputFirstName.text.toString()
                val lastName = binding.inputLastName.text.toString()
                val email = binding.inputEmail.text.toString()
                val githubLink = binding.inputGithub.text.toString()
                model.submitProject(email, firstName, lastName, githubLink)
            }
        }

        model.submissionStatus.observe(viewLifecycleOwner, { event ->
            event.getContentIfNotHandled()?.let {
                when (it) {
                    is Resource.Loading -> startLoading()
                    is Resource.Success -> {
                        CommonDialog(requireContext()).showSuccess()
                        stopLoading()
                        resetForm()
                    }
                    is Resource.Error -> {
                        CommonDialog(requireContext()).showError()
                        stopLoading()
                    }
                }
            }
        })
    }

    private fun validate(): Boolean {
        val firstName = binding.inputFirstName.text.toString()
        val lastName = binding.inputLastName.text.toString()
        val email = binding.inputEmail.text.toString()
        val githubLink = binding.inputGithub.text.toString()

        return if (firstName.isNotEmpty() && lastName.isNotEmpty() && email.isNotEmpty() && githubLink.isNotEmpty()) {
            true
        } else {
            Toast.makeText(requireContext(), "All fields are required", Toast.LENGTH_SHORT).show()
            false
        }
    }

    private fun startLoading() {
        binding.apply {
            loading.visibility = View.VISIBLE
            btnRegister.isEnabled = false
            inputFirstNameLayout.isEnabled = false
            inputLastNameLayout.isEnabled = false
            inputEmailLayout.isEnabled = false
            inputGithubLayout.isEnabled = false
        }
    }

    private fun stopLoading() {
        binding.apply {
            loading.visibility = View.INVISIBLE
            btnRegister.isEnabled = true
            inputFirstNameLayout.isEnabled = true
            inputLastNameLayout.isEnabled = true
            inputEmailLayout.isEnabled = true
            inputGithubLayout.isEnabled = true
        }
    }

    private fun resetForm() {
        binding.apply {
            inputFirstName.setText("")
            inputLastName.setText("")
            inputEmail.setText("")
            inputGithub.setText("")
        }
    }

    private fun View.hideKeyboard() {
        val inputMethodManager = requireContext().getSystemService(
            android.content.Context.INPUT_METHOD_SERVICE
        ) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(this.windowToken, 0)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
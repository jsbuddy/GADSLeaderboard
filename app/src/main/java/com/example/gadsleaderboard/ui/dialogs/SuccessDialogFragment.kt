package com.example.gadsleaderboard.ui.dialogs

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.gadsleaderboard.databinding.DialogSuccessBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class SuccessDialogFragment : DialogFragment() {

    private var _binding: DialogSuccessBinding? = null
    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val inflater = requireActivity().layoutInflater
            _binding = DialogSuccessBinding.inflate(inflater)

            val builder = MaterialAlertDialogBuilder(it)
                .setView(binding.root)

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
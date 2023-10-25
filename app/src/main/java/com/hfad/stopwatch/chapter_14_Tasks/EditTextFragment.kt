package com.hfad.stopwatch.chapter_14_Tasks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.hfad.stopwatch.R
import com.hfad.stopwatch.databinding.FragmentEditTextBinding

class EditTextFragment : Fragment() {
    private var _binding: FragmentEditTextBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEditTextBinding.inflate(inflater, container, false)
        val view = binding.root

        val taskId = EditTextFragmentArgs.fromBundle(requireArguments()).taskId

        val application = requireNotNull(this.activity).application
        val dao = TaskDatabase.getInstance(application).taskDao
        val viewModelFactory = EditTextViewModelFactory(taskId, dao)
        val viewModel = ViewModelProvider(this, viewModelFactory)[EditTaskViewModel::class.java]

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.navigateToList.observe(viewLifecycleOwner, Observer { navigate ->
            if (navigate) {
                view.findNavController().navigate(R.id.action_editTextFragment_to_tasksFragment)
                viewModel.onNavigateToList()
            }
        })
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
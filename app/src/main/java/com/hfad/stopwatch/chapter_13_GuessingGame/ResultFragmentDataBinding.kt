package com.hfad.stopwatch.chapter_13_GuessingGame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.hfad.stopwatch.R
import com.hfad.stopwatch.chapter_11_GuessingGame.ResultFragmentArgs
import com.hfad.stopwatch.databinding.FragmentResult13Binding


class ResultFragmentDataBinding : Fragment() {
    private var _binding: FragmentResult13Binding? = null
    private val binding get() = _binding!!

    lateinit var viewModel: ResultViewModelDataBinding
    lateinit var viewModelFactory: ResultViewModelFactoryDataBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResult13Binding.inflate(layoutInflater, container, false)
        val view = binding.root

        val result = ResultFragmentDataBindingArgs.fromBundle(requireArguments()).result
        viewModelFactory = ResultViewModelFactoryDataBinding(result)
        viewModel = ViewModelProvider(this, viewModelFactory)[ResultViewModelDataBinding::class.java]

        binding.apply {
            resultViewModel = viewModel

            newGameButton.setOnClickListener {
                view.findNavController()
                    .navigate(R.id.action_resultFragmentDataBinding_to_gameFragmentDataBinding)
            }
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
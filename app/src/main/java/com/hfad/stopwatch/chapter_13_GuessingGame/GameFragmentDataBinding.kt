package com.hfad.stopwatch.chapter_13_GuessingGame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.hfad.stopwatch.databinding.FragmentGame13Binding


class GameFragmentDataBinding : Fragment() {

    private lateinit var viewModel: GameViewModelDataBinding
    private var _binding: FragmentGame13Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGame13Binding.inflate(inflater, container, false)
        val view = binding.root
        viewModel = ViewModelProvider(this)[GameViewModelDataBinding::class.java]

        binding.apply {
            gameViewModel = viewModel
            lifecycleOwner = viewLifecycleOwner //позволяет макету реагировать на обновление данных LiveData
        }

        viewModel.gameOver.observe(viewLifecycleOwner, Observer { newValue ->
            if (newValue) {
                val action = GameFragmentDataBindingDirections
                    .actionGameFragmentDataBindingToResultFragmentDataBinding(viewModel.wonLostMessage())
                view.findNavController().navigate(action)
            }
        })

        binding.guessButton.setOnClickListener {
            viewModel.makeGuess(
                binding.guess.text.toString().uppercase()
            ) //получаем данные из editText

            //очистить ткстовое поле и обновить экран
            binding.guess.text = null

            //Если пользователь выиграл или проиграл, перейти к ResultFragment с передачей
            // возвращаемого значения wonLostMessage()
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
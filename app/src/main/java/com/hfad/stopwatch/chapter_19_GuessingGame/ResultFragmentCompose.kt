package com.hfad.stopwatch.chapter_19_GuessingGame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.hfad.stopwatch.R

class ResultFragmentCompose : Fragment() {
    lateinit var viewModel: ResultViewModelCompose
    lateinit var viewModelFactory: ResultViewModelFactoryCompose

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val result = ResultFragmentComposeArgs.fromBundle(requireArguments()).result
        viewModelFactory = ResultViewModelFactoryCompose(result)
        viewModel = ViewModelProvider(this, viewModelFactory)[ResultViewModelCompose::class.java]

        return ComposeView(requireContext()).apply {
            setContent {
                MaterialTheme {
                    Surface {
                        view?.let { ResultFragmentContent(it, viewModel) }
                    }
                }
            }
        }
    }

    @Composable
    fun ResultText(result: String) {
        Text(
            text = result,
            fontSize = 28.sp,
            textAlign = TextAlign.Center
        )
    }

    @Composable
    fun NewGameButton(clicked: () -> Unit) {
        Button(onClick = clicked) {
            Text(text = "Start New Game")
        }
    }

    @Composable
    fun ResultFragmentContent(view: View, viewModel: ResultViewModelCompose) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ResultText(result = viewModel.result)
            NewGameButton {
                view.findNavController()
                    .navigate(R.id.action_resultFragmentCompose_to_gameFragmentCompose)
            }
        }
    }
}
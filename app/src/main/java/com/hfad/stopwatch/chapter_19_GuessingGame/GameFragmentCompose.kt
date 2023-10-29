package com.hfad.stopwatch.chapter_19_GuessingGame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.hfad.stopwatch.R

class GameFragmentCompose : Fragment() {
    lateinit var viewModel: GameViewModelCompose

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[GameViewModelCompose::class.java]
        viewModel.gameOver.observe(viewLifecycleOwner, Observer { newValue ->
            if (newValue) {
                val action = GameFragmentComposeDirections
                    .actionGameFragmentComposeToResultFragmentCompose(viewModel.wonLostMessage())
                view?.findNavController()?.navigate(action)
            }
        })

        return ComposeView(requireContext()).apply {
            setContent {
                MaterialTheme {
                    Surface {
                        GameFragmentContent(viewModel)
                    }
                }
            }
        }
    }

    @Composable
    fun FinishGameButton(clicked: () -> Unit) {
        Button(onClick = clicked) {
            Text("Finish Game")
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun EnterGuess(guess: String, changed: (String) -> Unit) {
        TextField(value = guess,
            onValueChange = changed,
            label = { Text("Guess a letter") })
    }

    @Composable
    fun GuessButton(clicked: () -> Unit) {
        Button(onClick = clicked) {
            Text(text = "Guess!")
        }
    }

    @Composable
    fun IncorrectGuessesText(viewModel: GameViewModelCompose) {
        val incorrectGuesses = viewModel.incorrectGuesses.observeAsState()
        incorrectGuesses.value?.let {
            Text(text = stringResource(id = R.string.incorrect_guesses, it))
        }
    }

    @Composable
    fun LivesLeftText(viewModel: GameViewModelCompose) {
        val livesLeft = viewModel.livesLeft.observeAsState()
        livesLeft.value?.let {
            Text(text = stringResource(id = R.string.lives_left, it))
        }
    }

    @Composable
    fun SecretDisplay(viewModel: GameViewModelCompose) {
        val display = viewModel.secretWordDisplay.observeAsState()
        display.value?.let {
            Text(text = it, letterSpacing = 0.1.em, fontSize = 36.sp)
        }
    }

    @Composable
    fun GameFragmentContent(viewModel: GameViewModelCompose) {
        val guess = remember {
            mutableStateOf("")
        }

        Column(modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                SecretDisplay(viewModel = viewModel)
            }
            LivesLeftText(viewModel = viewModel)
            EnterGuess(guess = guess.value) { guess.value =it }

            Column(modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally) {
                GuessButton {
                    viewModel.makeGuess(guess.value.uppercase())
                    guess.value = ""
                }
                FinishGameButton {
                    viewModel.finishGame()
                }
            }
        }
    }
}
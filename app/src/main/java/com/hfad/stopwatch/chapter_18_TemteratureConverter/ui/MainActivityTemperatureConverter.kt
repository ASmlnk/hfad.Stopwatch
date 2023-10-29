package com.hfad.stopwatch.chapter_18_TemteratureConverter.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.hfad.stopwatch.R
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivityTemperatureConverter : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface {
                    MainActivityContent()
                }
            }
        }
    }

    companion object {
        fun newIntent(packageContext: Context): Intent {
            return Intent(packageContext, MainActivityTemperatureConverter::class.java)
        }
    }
}

@Composable
fun TemperatureText(celsius: Int) {
    val fahrenheit = (celsius.toDouble() * 9 / 5) + 32
    Text(text = "$celsius Celsius is $fahrenheit Fahrenheit")
}

@Composable
fun ConvertButton(clicked: () -> Unit) {
    Button(onClick = clicked) {
        Text(text = "Convert")
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnterTemperature(temperature: String, changed: (String) -> Unit) {
    TextField(
        value = temperature,
        onValueChange = changed,
        label = { Text(text = "Enter a temperature in Celsius") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    )
}

@Composable
fun MainActivityContent() {
    val celsius = remember { mutableStateOf(0) }
    val newCelsius = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Header(image = R.drawable.sunrise, description = "sunrise image")
        EnterTemperature(newCelsius.value) { newCelsius.value = it }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            ConvertButton {
                newCelsius.value.toIntOrNull()?.let {
                    celsius.value = it
                }
            }
        }
        TemperatureText(celsius = celsius.value)

    }

}

@Composable
fun Header(image: Int, description: String) {
    Image(
        painter = painterResource(id = image),
        contentDescription = description,
        modifier = Modifier
            .height(180.dp)
            .fillMaxWidth(),
        contentScale = ContentScale.Crop
    )

}

@Preview(showBackground = true)
@Composable
fun PreviewMainActivity() {
    MaterialTheme {
        Surface {
            MainActivityContent()
        }
    }

}


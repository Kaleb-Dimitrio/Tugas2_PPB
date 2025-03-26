package com.example.beningging

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.beningging.ui.theme.BeninggingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BeninggingTheme {
                MyApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {
    var shouldShowOnboarding by remember { mutableStateOf(true) }
    val isDarkMode = isSystemInDarkTheme()

    Surface(
        modifier = modifier,
        color = if (isDarkMode) Color.Black else MaterialTheme.colorScheme.background
    ) {
        if (shouldShowOnboarding) {
            OnboardingScreen(onContinueClicked = { shouldShowOnboarding = false })
        } else {
            Greetings()
        }
    }
}

@Composable
fun OnboardingScreen(onContinueClicked: () -> Unit, modifier: Modifier = Modifier) {
    val isDarkMode = isSystemInDarkTheme()
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Kaleb Dimitrio/5025221317 - Tugas 2 PPB", color = if (isDarkMode) Color.White else Color.Black)
        Button(
            onClick = onContinueClicked,
            modifier = Modifier.padding(vertical = 24.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isDarkMode) Color(0xFF8B0000) else MaterialTheme.colorScheme.primary,
                contentColor = Color.White
            )
        ) {
            Text("Lanjoed")
        }
    }
}

@Composable
fun Greetings(
    modifier: Modifier = Modifier,
    namesWithText: List<Pair<String, String>> = listOf(
        "1" to "Ketuhanan Yang Maha Esa.",
        "2" to "Kemanusiaan yang adil dan beradab.",
        "3" to "Persatuan Indonesia.",
        "4" to "Kerakyatan yang dipimpin oleh hikmat kebijaksanaan dalam permusyawaratan perwakilan.",
        "5" to "Keadilan sosial bagi seluruh rakyat Indonesia."
    )
) {
    Column(modifier = modifier.padding(vertical = 4.dp)) {
        namesWithText.forEach { (name, initialText) ->
            Greeting(name = name, initialText = initialText)
        }
    }
}

@Composable
fun Greeting(name: String, initialText: String, modifier: Modifier = Modifier) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    var expandedText by rememberSaveable { mutableStateOf(initialText) }
    val isDarkMode = isSystemInDarkTheme()

    Surface(
        color = Color.Red,
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(modifier = Modifier.weight(1f)) {
                Text("Pancasila", color = Color.White)
                Text(name, color = Color.White)
                if (expanded) {
                    OutlinedTextField(
                        value = expandedText,
                        onValueChange = { expandedText = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        textStyle = LocalTextStyle.current.copy(color = Color.Black),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Black,
                            unfocusedBorderColor = Color.Black,
                            cursorColor = Color.Black,
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black
                        )
                    )
                }
            }
            ElevatedButton(
                onClick = { expanded = !expanded },
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = if (isDarkMode) Color(0xFF8B0000) else MaterialTheme.colorScheme.primary,
                    contentColor = Color.White
                )
            ) {
                Text(if (expanded) "Show Less" else "Show More")
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun GreetingPreview() {
    BeninggingTheme {
        Greetings()
    }
}

@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    BeninggingTheme {
        MyApp(Modifier.fillMaxSize())
    }
}

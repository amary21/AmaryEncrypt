package com.amary.encrypt.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.amary.encrypt.Greeting

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GreetingView(
                        Greeting().encrypt(),
                        Greeting().decrypt(),
                    )
                }
            }
        }
    }
}

@Composable
fun GreetingView(
    textEncrypt: String,
    textDecrypt: String,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Encrypt")
        Text(text = textEncrypt)

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Decrypt")
        Text(text = textDecrypt)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFAFAFA)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView(
            "Hello, Android!",
            "Hello, Android!",
        )
    }
}

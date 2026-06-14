package com.andreich.bookreader.ui.authscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AuthScreen(onAuthClick: (String, String) -> Unit, onGoogleAuthClick: (String, String) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        val email = rememberTextFieldState()
        val password = rememberTextFieldState()
        Box(modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)) {
            OutlinedTextField(state = email, label = { Text("Email") })
        }
        Box(modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)) {
            OutlinedTextField(state = password, label = { Text("Password") })
        }
        Button(onClick = {
            onAuthClick(email.text.toString(), password.text.toString())
        }) {
            Text(text = "Sign in")
        }
        Button(onClick = {
            onGoogleAuthClick(email.text.toString(), password.text.toString())
        }) {
            Text(text = "Sign in with Google")
        }
    }
}
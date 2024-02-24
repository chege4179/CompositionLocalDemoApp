package com.peterchege.compositionlocaldemo.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.peterchege.compositionlocaldemo.ui.state.LocalAppStateProvider
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen() {
    val appState = LocalAppStateProvider.current
    val stringAppState by appState.appState.collectAsStateWithLifecycle(initialValue = "default")
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Text(text = stringAppState)

            Button(
                onClick = {
                    scope.launch {
                        appState.updateAppState("settings")
                    }
                }
            ) {
                Text(text = "Update State")
            }
        }
    }
}
package com.peterchege.compositionlocaldemo.ui.state

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import com.peterchege.compositionlocaldemo.core.StateProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

val LocalAppStateProvider = compositionLocalOf<AppState> {
    NoOpState()
}

interface AppState{
    val appState : Flow<String>

    suspend fun updateAppState(text: String)
}


class NoOpState :AppState{
    override val appState: Flow<String> = flow { "no-op" }

    override suspend fun updateAppState(text: String) {
        TODO("Not yet implemented")
    }
}
class AppStateImpl @Inject constructor(
    private val stateProvider: StateProvider
) :AppState {

    override val appState = stateProvider.getState()


    override suspend fun updateAppState(text:String){
        stateProvider.setState(text)
    }
}
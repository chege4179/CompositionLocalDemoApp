package com.peterchege.compositionlocaldemo.core

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.peterchege.compositionlocaldemo.ui.state.AppState
import com.peterchege.compositionlocaldemo.ui.state.AppStateImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatastorePreferences(@ApplicationContext context: Context):
            DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = {
                context.preferencesDataStoreFile(name = "app_state")
            }
        )
    }


    @Provides
    @Singleton
    fun provideUserPreferencesRepository(
        dataStore: DataStore<Preferences>
    ): StateProvider {
        return StateProvider(dataStore = dataStore)
    }

    @Provides
    @Singleton
    fun provideAppState(
        stateProvider: StateProvider
    ): AppState {
        return AppStateImpl(stateProvider = stateProvider)
    }
}
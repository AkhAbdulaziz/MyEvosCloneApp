package uz.gita.myevoscloneapp.di

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.myevoscloneapp.app.App
import uz.gita.myevoscloneapp.model.local.LocalStorage
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun getLocalStorage() = LocalStorage()

    @Provides
    fun getFirebaseDatabase() = Firebase.firestore
}
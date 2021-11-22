package uz.gita.myevoscloneapp.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer

fun <T, K> MediatorLiveData<K>.addSourceDisposable(liveData: LiveData<T>, observer: Observer<T>) {
    addSource(liveData) {
        observer.onChanged(it)
        removeSource(liveData)
    }
}
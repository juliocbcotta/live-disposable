package br.com.live.disposable


import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.subscribe(body: (T) -> Unit): LiveDisposable<T> {
    val observer = Observer<T> { t ->
        if (t != null) {
            body(t)
        }
    }
    observeForever(observer)
    return LiveDisposable(this, observer)
}

class LiveCompositeDisposable {
    private val disposables = mutableListOf<LiveDisposable<*>>()
    fun add(disposable: LiveDisposable<*>) {
        disposables.add(disposable)
    }

    fun clear() {
        disposables.forEach {
            it.dispose()
        }
        disposables.clear()
    }
}

class LiveDisposable<T>(private val liveData: LiveData<T>, private val observer: Observer<T>) {

    fun dispose() {
        liveData.removeObserver(observer)
    }
}
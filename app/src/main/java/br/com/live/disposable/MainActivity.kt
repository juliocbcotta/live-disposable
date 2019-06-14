package br.com.live.disposable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData

class MainActivity : AppCompatActivity() {
    private val liveData = MutableLiveData<String>()
    private val disposable = LiveCompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        disposable.add(liveData.subscribe { })
    }

    override fun onDestroy() {
        disposable.clear()
        super.onDestroy()
    }
}

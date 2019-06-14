package br.com.live.disposable


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class LiveDisposableTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun `should subscribe the liveData and change the value`() {
        val input = MutableLiveData<String>()
        val value = "a"
        var expected: String? = ""
        input.subscribe {
            expected = it
        }
        input.value = value
        assertEquals(expected, value)
    }

    @Test
    fun `should subscribe the liveData and disposable`() {
        val input = MutableLiveData<String>()
        val valueA = "a"
        val valueB = "b"
        var expected: String? = ""

        val disposable = input.subscribe {
            expected = it
        }

        input.value = valueA
        assertEquals(expected, valueA)

        disposable.dispose()

        input.value = valueB
        assertEquals(expected, valueA)
    }


    @Test
    fun `should dispose all subscribe`() {
        val inputA = MutableLiveData<String>()
        val valueA = "a"
        val valueB = "b"
        var expectedA: String? = ""
        val compositeDisposable = LiveCompositeDisposable()

        val disposableA = inputA.subscribe {
            expectedA = it
        }

        compositeDisposable.add(disposableA)

        inputA.value = valueA
        assertEquals(expectedA, valueA)

        compositeDisposable.clear()

        inputA.value = valueB
        assertEquals(expectedA, valueA)
    }
}
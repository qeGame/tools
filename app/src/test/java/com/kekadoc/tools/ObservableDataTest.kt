package com.kekadoc.tools

import com.kekadoc.tools.observable.MutableData
import com.kekadoc.tools.observable.MutableData.Companion.toMutable
import com.kekadoc.tools.observable.Observable
import com.kekadoc.tools.observable.ObservableData
import com.kekadoc.tools.observable.ObservableData.Companion.observe
import com.kekadoc.tools.observable.ObservableData.Companion.toObservable
import com.kekadoc.tools.observable.observer
import org.junit.Test

class ObservableDataTest {

    companion object {
        private const val TAG: String = "ObservableDataTest-TAG"
    }

    private val dataCode = MutableData(0)
    val code: ObservableData<Int>
        get() = dataCode

    var expectedNewCode = -5
    var expectedOldCode = 5

    @Test
    fun case() {
        val observing = code.observe(observer { println(it) })
        dataCode.setValue(5)
        observing.remove()
        dataCode.setValue(10)
    }

    @Test
    fun events() {
        var onObservingActive = false
        var onObservingInactive = false

        val codeData = object : MutableData<Int>(expectedOldCode) {
            override fun onChange(oldValue: Int, newValue: Int) {
                println(oldValue)
                assert(oldValue == expectedOldCode)
                assert(newValue == expectedNewCode)
            }
            override fun onActive() {
                onObservingActive = true
            }
            override fun onInactive() {
                onObservingInactive = true
            }

        }

        codeData.observe(observer {  }).remove()

        codeData.setValue(expectedNewCode)

        assert(onObservingActive && onObservingInactive)
    }
    @Test
    fun observe() {
        val codeData = MutableData(expectedOldCode)
        val obs = codeData.observe {_, old, new ->
            assert(old == expectedOldCode) {
                "old: $old != new: $expectedOldCode"
            }
            assert(new == expectedOldCode) {
                "old: $new != new: $expectedOldCode"
            }
        }
        obs.remove()
        var first = true
        codeData.observe {_, old, new ->
            if (first) {
                first = false
                assert(old == expectedOldCode) {
                    "old: $old != expectedOldCode: $expectedOldCode"
                }
                assert(new == expectedOldCode) {
                    "new: $new != expectedOldCode: $expectedOldCode"
                }
            } else {
                assert(old == expectedOldCode) {
                    "old: $old != expectedOldCode: $expectedOldCode"
                }
                assert(new == expectedNewCode) {
                    "new: $new != expectedNewCode: $expectedNewCode"
                }
            }

        }
        codeData.setValue(expectedNewCode)
    }
    @Test
    fun updateValue() {
        var count = 0
        val codeData = object : MutableData<Int>(expectedOldCode) {
            override fun onChange(oldValue: Int, newValue: Int) {
                super.onChange(oldValue, newValue)
                count++
            }
        }
        codeData.updateValue(expectedNewCode)
        codeData.updateValue(expectedNewCode)
        codeData.updateValue(expectedNewCode)

        assert(count == 3)
    }
    @Test
    fun setValue() {
        var changed = false
        val codeData = object : MutableData<Int>(expectedOldCode) {
            override fun onChange(oldValue: Int, newValue: Int) {
                super.onChange(oldValue, newValue)
                assert(!changed)
                changed = true
            }
        }
        codeData.setValue(expectedNewCode)
        codeData.setValue(expectedNewCode)
    }
    @Test
    fun toObservable() {
        val data = "Data"
        val observable = data.toObservable()
        observable.observe(observer { assert(it == data) })
    }
    @Test
    fun observeObservable() {
        var called = false
        var secondCall = 0
        val observable = MutableData(5)
        val observableSecond = MutableData(0)
        observableSecond.observe(observer {
            secondCall = it
            called = true
        })
        observable.observe(observableSecond)
        observable.setValue(expectedNewCode)
        assert(called && secondCall == expectedNewCode)
    }

}
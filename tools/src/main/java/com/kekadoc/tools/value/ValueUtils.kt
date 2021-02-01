package com.kekadoc.tools.value

import kotlin.math.pow
import kotlin.math.roundToInt

object ValueUtils {

    @JvmOverloads
    fun setValueInRange(from: Long, to: Long, newValue: Long, event: RangeChangeEvents<Long>? = null): Long {
        var value = newValue
        if (newValue > to) {
            val overflow = value - to
            value = to
            event?.onOverflow(from, to, value, overflow)
            event?.onMax(from, to)
        } else if (value < from) {
            val overflow = value - from
            value = from
            event?.onOverflow(from, to, value, overflow)
            event?.onMin(from, to)
        }
        event?.onChange(from, to, value, value)
        return value
    }
    @JvmOverloads
    fun addValueInRange(from: Long, to: Long, currentValue: Long, addition: Long, event: RangeChangeEvents<Long>? = null): Long {
        var value = currentValue + addition
        if (value > to) {
            val overflow = value - to
            value = to
            event?.onOverflow(from, to, value, overflow)
            event?.onMax(from, to)
        } else if (value < from) {
            val overflow = value - from
            value = from
            event?.onOverflow(from, to, value, overflow)
            event?.onMin(from, to)
        }
        event?.onChange(from, to, value, value - currentValue)
        return value
    }

    @JvmOverloads
    fun setValueInRange(from: Int, to: Int, newValue: Int, event: RangeChangeEvents<Int>? = null): Int {
        var value = newValue
        if (newValue > to) {
            val overflow = value - to
            value = to
            event?.onOverflow(from, to, value, overflow)
            event?.onMax(from, to)
        } else if (value < from) {
            val overflow = value - from
            value = from
            event?.onOverflow(from, to, value, overflow)
            event?.onMin(from, to)
        }
        event?.onChange(from, to, value, value)
        return value
    }
    @JvmOverloads
    fun addValueInRange(from: Int, to: Int, currentValue: Int, addition: Int, event: RangeChangeEvents<Int>? = null): Int {
        var value = currentValue + addition
        if (value > to) {
            val overflow = value - to
            value = to
            event?.onOverflow(from, to, value, overflow)
            event?.onMax(from, to)
        } else if (value < from) {
            val overflow = value - from
            value = from
            event?.onOverflow(from, to, value, overflow)
            event?.onMin(from, to)
        }
        event?.onChange(from, to, value, value - currentValue)
        return value
    }

    @JvmOverloads
    fun setValueInRange(from: Double, to: Double, newValue: Double, event: RangeChangeEvents<Double>? = null): Double {
        var value = newValue
        if (newValue > to) {
            val overflow = value - to
            value = to
            event?.onOverflow(from, to, value, overflow)
            event?.onMax(from, to)
        } else if (value < from) {
            val overflow = value - from
            value = from
            event?.onOverflow(from, to, value, overflow)
            event?.onMin(from, to)
        }
        event?.onChange(from, to, value, value)
        return value
    }
    @JvmOverloads
    fun addValueInRange(from: Double, to: Double, currentValue: Double, addition: Float, event: RangeChangeEvents<Double>? = null): Double {
        var value = currentValue + addition
        if (value > to) {
            val overflow = value - to
            value = to
            event?.onOverflow(from, to, value, overflow)
            event?.onMax(from, to)
        } else if (value < from) {
            val overflow = value - from
            value = from
            event?.onOverflow(from, to, value, overflow)
            event?.onMin(from, to)
        }
        event?.onChange(from, to, value, value - currentValue)
        return value
    }

    @JvmOverloads
    fun setValueInRange(from: Float, to: Float, newValue: Float, event: RangeChangeEvents<Float>? = null): Float {
        var value = newValue
        if (newValue > to) {
            val overflow = value - to
            value = to
            event?.onOverflow(from, to, value, overflow)
            event?.onMax(from, to)
        } else if (value < from) {
            val overflow = value - from
            value = from
            event?.onOverflow(from, to, value, overflow)
            event?.onMin(from, to)
        }
        event?.onChange(from, to, value, value)
        return value
    }
    @JvmOverloads
    fun addValueInRange(from: Float, to: Float, currentValue: Float, addition: Float, event: RangeChangeEvents<Float>? = null): Float {
        var value = currentValue + addition
        if (value > to) {
            val overflow = value - to
            value = to
            event?.onOverflow(from, to, value, overflow)
            event?.onMax(from, to)
        } else if (value < from) {
            val overflow = value - from
            value = from
            event?.onOverflow(from, to, value, overflow)
            event?.onMin(from, to)
        }
        event?.onChange(from, to, value, value - currentValue)
        return value
    }

    interface RangeChangeEvents<Value> {
        fun onChange(min: Value, max: Value, current: Value, change: Value) {}
        fun onOverflow(min: Value, max: Value, current: Value, overflow: Value) {}
        fun onMax(min: Value, max: Value) {}
        fun onMin(min: Value, max: Value) {}
    }

    /** Округляет полученное число, до countZero знаков после запятой  */
    @JvmStatic fun round(value: Float, countZero: Int): Float {
        val zero: Int = 10.0.pow(countZero).toInt()
        return (value * zero).roundToInt().toFloat() / zero
    }

    /** Округляет полученное число, до countZero знаков после запятой  */
    @JvmStatic fun round(value: Double, countZero: Int): Double {
        val zero: Int = 10.0.pow(countZero).toInt()
        return (value * zero).roundToInt().toDouble() / zero
    }

    @JvmStatic fun getRangeValue(from: Int, to: Int): Int {
        return to - from
    }
    @JvmStatic fun getRangeValue(from: Float, to: Float): Float {
        return to - from
    }
    @JvmStatic fun getRangeValue(from: Double, to: Double): Double {
        return to - from
    }
    @JvmStatic fun getRangeValue(from: Long, to: Long): Long {
        return to - from
    }

    @JvmStatic fun average(vararg floats: Float): Float {
        var s = 0.0f
        for (p in floats) s += p
        return s / floats.size
    }
    @JvmStatic fun average(vararg ints: Int): Int {
        var s = 0
        for (p in ints) s += p
        return s / ints.size
    }
    @JvmStatic fun average(vararg doubles: Double): Double {
        var s = 0.0
        for (p in doubles) s += p
        return s / doubles.size
    }
    @JvmStatic fun average(vararg longs: Long): Long {
        var s: Long = 0
        for (p in longs) s += p
        return s / longs.size
    }


    @JvmStatic fun <T> averageFloat(items: Collection<T>, processor: (item: T) -> Float): Float {
        var s = 0f
        for (item in items) s += processor.invoke(item)
        return s / items.size
    }
    @JvmStatic fun <T> averageFloat(processor: (item: T) -> Float, vararg items: T): Float {
        var s = 0f
        for (item in items) s += processor.invoke(item)
        return s / items.size
    }

    @JvmStatic fun <T> averageInt(items: Collection<T>, processor: (item: T) -> Int): Int {
        var s = 0
        for (item in items) s += processor.invoke(item)
        return s / items.size
    }
    @JvmStatic fun <T> averageInt(processor: (item: T) -> Int, vararg items: T): Int {
        var s = 0
        for (item in items) s += processor.invoke(item)
        return s / items.size
    }

    @JvmStatic fun <T> averageDouble(items: Collection<T>, processor: (item: T) -> Double): Double {
        var s = 0.0
        for (item in items) s += processor.invoke(item)
        return s / items.size
    }
    @JvmStatic fun <T> averageDouble(processor: (item: T) -> Double, vararg items: T): Double {
        var s = 0.0
        for (item in items) s += processor.invoke(item)
        return s / items.size
    }

    @JvmStatic fun <T> averageLong(items: Collection<T>, processor: (item: T) -> Long): Long {
        var s = 0L
        for (item in items) s += processor.invoke(item)
        return s / items.size
    }
    @JvmStatic fun <T> averageLong(processor: (item: T) -> Long, vararg items: T): Long {
        var s = 0L
        for (item in items) s += processor.invoke(item)
        return s / items.size
    }

    @JvmStatic fun makePercent(vararg percent: Float): FloatArray {
        var sum = 0f
        for (p in percent) sum += p
        val make = FloatArray(percent.size)
        for (i in percent.indices) make[i] = percent[i] / sum * 100f
        return make
    }

    @JvmStatic fun convertToAnotherPercent(percent: Float, anotherPercent: Float): Float {
        return percent * (anotherPercent / 100f)
    }

    @JvmStatic fun boolEquals(bool: Boolean, valBool: Int): Boolean {
        if (valBool > 1 || valBool < 0) return false
        return if (bool && valBool == 1) true else !bool && valBool == 0
    }
    @JvmStatic fun boolVal(bool: Boolean): Int {
        return if (bool) 1 else 0
    }

}
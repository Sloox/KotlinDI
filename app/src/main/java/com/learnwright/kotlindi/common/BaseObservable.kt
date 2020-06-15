package com.learnwright.kotlindi.common

import java.util.*
import java.util.concurrent.ConcurrentHashMap

abstract class BaseObservable<T> {
    // thread-safe set of listeners
    private val mListeners: MutableSet<T> = Collections.newSetFromMap(ConcurrentHashMap<T, Boolean>(1))


    fun registerListener(listener: T) {
        mListeners.add(listener)
    }

    fun unregisterListener(listener: T) {
        mListeners.remove(listener)
    }

    /**
     * Get a reference to the unmodifiable set containing all the registered listeners.
     */
    protected fun getListeners(): Set<T> {
        return Collections.unmodifiableSet(mListeners)
    }

}
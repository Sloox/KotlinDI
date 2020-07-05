package com.learnwright.kotlindi.di.mvcviews

/**
 * This interface corresponds to MVC views that need to notify MVC controllers of input events
 */
interface ObservableViewMvc<ListenerType> : ViewMvc {
    /**
     * Register a listener that will be notified of any input events performed on this MVC view
     */
    fun registerListener(listener: ListenerType)

    /**
     * Unregister a previously registered listener. Does nothing if the listener wasn't registered.
     */
    fun unregisterListener(listener: ListenerType)
}
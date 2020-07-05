package com.learnwright.kotlindi.di.mvcviews

import android.content.Context
import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import com.learnwright.kotlindi.common.BaseObservable

/**
 * This is the base class which provides basic common functionality to MVC views implementations
 */
abstract class BaseViewMvc<ListenerType> : BaseObservable<ListenerType>(), ObservableViewMvc<ListenerType> {
    /**
     * Set the root android view of this MVC view
     */
    // ---------------------------------------------------------------------------------------------
    // region root View
    override lateinit var rootView: View
        protected set

    // endregion root View
    // ---------------------------------------------------------------------------------------------
    /**
     * Convenience method for obtaining references to [View]s contained in the hierarchy of
     * the root [View].<br></br>
     * This method uses Java's type inference in order to automatically cast the returned
     * [View]s to the type of the containing variable.
     *
     * @return [View] object casted to the type inferred by Java's automatic type inference
     * mechanism, or null
     */
    protected fun <T : View?> findViewById(@IdRes id: Int): T {
        return rootView.findViewById<View>(id) as T
    }

    /**
     * Convenience method for obtaining reference to [Context]
     *
     * @return instance of [Context] associated with the root [View] of this MVC view
     */
    protected val context: Context
        get() = rootView.context

    /**
     * Convenience method for obtaining a string resource
     */
    protected fun getString(@StringRes id: Int): String {
        return context.getString(id)
    }

    /**
     * Convenience method for obtaining a string resource with format arguments.
     */
    protected fun getString(@StringRes id: Int, vararg formatArgs: Any?): String {
        return context.getString(id, *formatArgs)
    }
}
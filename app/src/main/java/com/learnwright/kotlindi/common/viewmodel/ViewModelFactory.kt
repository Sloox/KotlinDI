package com.learnwright.kotlindi.common.viewmodel

import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Provider

class ViewModelFactory(providerMap: Map<Class<out ViewModel?>, Provider<ViewModel>>) : ViewModelProvider.Factory {
    private val mProviderMap: Map<Class<out ViewModel?>, Provider<ViewModel>> = providerMap

    @NonNull
    override fun <T : ViewModel?> create(@NonNull modelClass: Class<T?>): T {
        return (mProviderMap[modelClass] ?: error("Null provider map")).get() as T
    }

}

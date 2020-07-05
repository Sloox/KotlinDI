package com.learnwright.kotlindi.di.presentation

import androidx.lifecycle.ViewModel
import com.learnwright.kotlindi.common.viewmodel.ViewModelFactory
import com.learnwright.kotlindi.screens.questiondetails.QuestionDetailsViewModel
import com.learnwright.kotlindi.screens.questionlist.QuestionsListViewModel
import com.learnwright.kotlindi.usecases.questions.FetchQuestionDetailUC
import dagger.MapKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Provider
import kotlin.reflect.KClass

@Module
class KotlinViewModelModule {
    @MustBeDocumented
    @kotlin.annotation.Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
    @kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
    @MapKey
    internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

    @Provides
    fun viewModelFactory(providerMap: Map<Class<out ViewModel>, Provider<ViewModel>>): ViewModelFactory {
        return ViewModelFactory(providerMap)
    }

    @Provides
    @IntoMap
    @ViewModelKey(QuestionDetailsViewModel::class)
    fun questionDetailsViewModel(fetchQuestionDetailsUseCase: FetchQuestionDetailUC): ViewModel {
        return QuestionDetailsViewModel(fetchQuestionDetailsUseCase)
    }

    @Provides
    @IntoMap
    @ViewModelKey(QuestionsListViewModel::class)
    fun questionsListViewModel(): ViewModel {
        return QuestionsListViewModel()
    }
}

package com.vladder2312.studentdocs.di

import android.content.Context
import com.vladder2312.studentdocs.ui.add_document.AddDocumentFragment
import com.vladder2312.studentdocs.ui.documents.DocumentsFragment
import com.vladder2312.studentdocs.ui.options.OptionsFragment
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val context: Context) {

    @Provides
    fun provideContext() : Context {
        return context
    }

    @Provides
    fun provideDocumentsFragment() : DocumentsFragment {
        return DocumentsFragment()
    }

    @Provides
    fun provideAddDocumentFragment(): AddDocumentFragment {
        return AddDocumentFragment()
    }

    @Provides
    fun provideOptionsFragment() : OptionsFragment {
        return OptionsFragment()
    }
}
package com.vladder2312.studentdocs.di

import android.content.Context
import com.vladder2312.studentdocs.data.db.DocDao
import com.vladder2312.studentdocs.data.db.DocDatabase
import com.vladder2312.studentdocs.data.repositories.DocRepository
import com.vladder2312.studentdocs.ui.add_document.AddDocumentFragment
import com.vladder2312.studentdocs.ui.add_document.AddDocumentPresenter
import com.vladder2312.studentdocs.ui.documents.DocumentsFragment
import com.vladder2312.studentdocs.ui.documents.DocumentsPresenter
import com.vladder2312.studentdocs.ui.main.MainActivity
import com.vladder2312.studentdocs.ui.options.OptionsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun provideContext() : Context
    fun provideDocumentsFragment() : DocumentsFragment
    fun provideAddDocumentFragment() : AddDocumentFragment
    fun provideOptionsFragment() : OptionsFragment
    fun provideDocDatabase() : DocDatabase
    fun provideDocDao() : DocDao
    fun provideDocRepository() : DocRepository

    fun inject(mainActivity: MainActivity)
    fun inject(addDocumentPresenter: AddDocumentPresenter)
    fun inject(documentsPresenter: DocumentsPresenter)
}
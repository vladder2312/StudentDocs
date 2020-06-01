package com.vladder2312.studentdocs.di

import android.content.Context
import androidx.room.Room
import com.vladder2312.studentdocs.data.db.DocDao
import com.vladder2312.studentdocs.data.db.DocDatabase
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

    @Provides
    fun provideDocDatabase() : DocDatabase {
        return Room.databaseBuilder(context, DocDatabase::class.java, "Database")
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    fun provideDocDao() : DocDao {
        return provideDocDatabase().docDao
    }


}
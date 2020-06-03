package com.vladder2312.studentdocs.ui.document

import com.arellomobile.mvp.MvpPresenter
import com.vladder2312.studentdocs.App
import com.vladder2312.studentdocs.data.repositories.DocRepository
import javax.inject.Inject

class DocumentPresenter : MvpPresenter<DocumentView>() {

    @Inject
    lateinit var docRepository: DocRepository
    val model = DocumentModel()

    init {
        App.appComponent.inject(this)
    }
}
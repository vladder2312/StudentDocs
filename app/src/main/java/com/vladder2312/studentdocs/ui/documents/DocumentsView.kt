package com.vladder2312.studentdocs.ui.documents

import com.arellomobile.mvp.MvpView
import com.vladder2312.studentdocs.domain.Document

interface DocumentsView : MvpView {

    fun initRecycler()
    fun setData(documents: List<Document>)
    fun startDocumentActivity(document: Document)
}
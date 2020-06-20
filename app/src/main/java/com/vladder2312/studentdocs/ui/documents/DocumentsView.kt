package com.vladder2312.studentdocs.ui.documents

import com.arellomobile.mvp.MvpView
import com.vladder2312.studentdocs.domain.Document

/**
 * Интерфейс View просмотра документов
 */
interface DocumentsView : MvpView {

    fun initViews()
    fun setData(documents: List<Document>)
    fun startDocumentActivity(document: Document)
}
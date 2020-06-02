package com.vladder2312.studentdocs.ui.add_document

import com.arellomobile.mvp.MvpView

interface AddDocumentView : MvpView {

    fun initRecycler()
    fun initListeners()
    fun initViews()
}
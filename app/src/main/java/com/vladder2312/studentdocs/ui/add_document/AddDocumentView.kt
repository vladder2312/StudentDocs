package com.vladder2312.studentdocs.ui.add_document

import com.arellomobile.mvp.MvpView
import com.vladder2312.studentdocs.domain.Photo

interface AddDocumentView : MvpView {

    fun initRecycler()
    fun initListeners()
    fun initViews()
    fun openCamera()
    fun openGallery()
    fun showPhotos(photos: MutableList<Photo>)
    fun showMessage(text: String)
}
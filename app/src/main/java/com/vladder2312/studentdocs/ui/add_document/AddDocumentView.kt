package com.vladder2312.studentdocs.ui.add_document

import com.arellomobile.mvp.MvpView
import com.vladder2312.studentdocs.domain.Photo

interface AddDocumentView : MvpView {

    fun initRecycler()
    fun initListeners()
    fun initViews()
    fun openCamera()
    fun openGallery()
    fun addToAdapter(photos: MutableList<Photo>)
    fun startPhotoActivity(uri: String)
    fun showMessage(text: String)
    fun closeFragment()
    fun showPhotoDeleteDialog(photo: Photo)
}
package com.vladder2312.studentdocs.ui.document

import com.arellomobile.mvp.MvpView
import com.vladder2312.studentdocs.domain.Photo

interface DocumentView : MvpView {

    fun initRecycler()
    fun initSpinner()
    fun initViews()
    fun initListeners()
    fun setPhotos(photos: List<Photo>)
    fun startPhotoActivity(uri: String)
    fun changeMode(on: Boolean)
    fun openCamera()
    fun openGallery()
    fun showPhotoDeleteDialog(photo: Photo)
}
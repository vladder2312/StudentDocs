package com.vladder2312.studentdocs.ui.add_document

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.vladder2312.studentdocs.domain.Category
import com.vladder2312.studentdocs.domain.Photo

@InjectViewState
class AddDocumentPresenter : MvpPresenter<AddDocumentView>() {

    val model = AddDocumentModel()

    fun nameChanged(name: String) {
        model.name = name
    }

    fun categorySelected(index: Int) {
        when (index) {
            0 -> {
                model.category = Category.Document
            }
            1 -> {
                model.category = Category.Certificate
            }
            2 -> {
                model.category = Category.Reward
            }
        }
    }

    fun photoLoaded(utl: String) {
        model.photos.add(Photo(System.currentTimeMillis().toString(), model.id, utl))
        viewState.showPhotos(model.photos)
    }
}
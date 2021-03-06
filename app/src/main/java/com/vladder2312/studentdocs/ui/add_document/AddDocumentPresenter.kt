package com.vladder2312.studentdocs.ui.add_document

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.vladder2312.studentdocs.App
import com.vladder2312.studentdocs.data.repositories.DocRepository
import com.vladder2312.studentdocs.domain.Category
import com.vladder2312.studentdocs.domain.Document
import com.vladder2312.studentdocs.domain.Photo
import javax.inject.Inject

/**
 * Презентер фрагмента добавления документа
 */
@InjectViewState
class AddDocumentPresenter : MvpPresenter<AddDocumentView>() {

    @Inject
    lateinit var docRepository: DocRepository
    val model = AddDocumentModel()

    init {
        App.appComponent.inject(this)
    }

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
        viewState.addToAdapter(model.photos)
    }

    fun saveDocument() {
        if (model.name.trim() == "") {
            viewState.showMessage("Введите название документа")
        } else if(model.photos.size == 0) {
            viewState.showMessage("Добавьте фотографию")
        } else {
            docRepository.insertDocument(
                Document(
                    model.id,
                    model.name,
                    model.category,
                    System.currentTimeMillis() / 1000L,
                    model.photos[0].uri
                )
            )
            for(photo in model.photos){
                docRepository.insertPhoto(photo)
            }
            viewState.showMessage("Успешно добавлено")
            viewState.closeFragment()
        }
    }

    fun deletePhoto(photo: Photo){
        model.photos.remove(photo)
        viewState.addToAdapter(model.photos)
    }
}
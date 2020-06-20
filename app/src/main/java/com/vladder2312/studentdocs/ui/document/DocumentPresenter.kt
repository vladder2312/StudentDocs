package com.vladder2312.studentdocs.ui.document

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.vladder2312.studentdocs.App
import com.vladder2312.studentdocs.data.repositories.DocRepository
import com.vladder2312.studentdocs.domain.Category
import com.vladder2312.studentdocs.domain.Document
import com.vladder2312.studentdocs.domain.Photo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Презентер фрагмента с информацией о документе
 */
@InjectViewState
class DocumentPresenter : MvpPresenter<DocumentView>() {

    @Inject
    lateinit var docRepository: DocRepository
    val model = DocumentModel()

    init {
        App.appComponent.inject(this)
    }

    fun nameChanged(name: String) {
        model.name = name
    }

    fun saveToModel(document: Document){
        model.id = document.id
        model.name = document.name
        model.category = document.category
        model.createdDate = document.createdDate
    }

    fun addPhoto(uri: String) {
        val photo = Photo(System.currentTimeMillis().toString(), model.id, uri)
        model.photos.add(photo)
        docRepository.insertPhoto(photo)
        loadPhotos()
    }

    fun deletePhoto(photo: Photo) {
        model.photos.remove(photo)
        docRepository.deletePhoto(photo.id)
        loadPhotos()
    }

    fun deleteDocument(){
        docRepository.deleteDocument(model.id)
    }

    fun updateDocument(){
        model.createdDate = System.currentTimeMillis()
        docRepository.updateDocument(Document(
            model.id,
            model.name,
            model.category,
            model.createdDate,
            model.photos[0].uri
        ))
    }

    fun categorySelected(position: Int) {
        when(position){
            0 -> model.category = Category.Document
            1 -> model.category = Category.Certificate
            2 -> model.category = Category.Reward
        }
    }

    fun loadPhotos() {
        val disposable = docRepository.getPhotos(model.id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                model.photos.clear()
                model.photos.addAll(it)
                viewState.setPhotos(model.photos)
            }
    }
}
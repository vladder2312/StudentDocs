package com.vladder2312.studentdocs.ui.document

import android.net.Uri
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.vladder2312.studentdocs.App
import com.vladder2312.studentdocs.data.repositories.DocRepository
import com.vladder2312.studentdocs.domain.Photo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@InjectViewState
class DocumentPresenter : MvpPresenter<DocumentView>() {

    @Inject
    lateinit var docRepository: DocRepository
    val model = DocumentModel()

    init {
        App.appComponent.inject(this)
    }

    fun nameChanged(name: String) {

    }

    fun addPhoto(uri: Uri) {

    }

    fun deletePhoto(photo: Photo) {

    }

    fun categorySelected(position: Int) {

    }

    fun loadPhotos(documentId: String) {
        val disposable = docRepository.getPhotos(documentId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                model.photos.clear()
                model.photos.addAll(it)
                viewState.setPhotos(model.photos)
            }
    }
}
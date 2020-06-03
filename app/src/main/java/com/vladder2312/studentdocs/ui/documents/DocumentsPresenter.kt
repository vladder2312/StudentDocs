package com.vladder2312.studentdocs.ui.documents

import androidx.room.RxRoom
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.vladder2312.studentdocs.App
import com.vladder2312.studentdocs.data.repositories.DocRepository
import com.vladder2312.studentdocs.domain.Document
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@InjectViewState
class DocumentsPresenter : MvpPresenter<DocumentsView>() {

    @Inject
    lateinit var docRepository: DocRepository
    val model = DocumentsModel()

    init {
        App.appComponent.inject(this)
    }

    fun loadDocuments() {
        val disposable = docRepository.getDocuments()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                viewState.setData(it)
            }
    }
}
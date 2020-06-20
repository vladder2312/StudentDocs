package com.vladder2312.studentdocs.ui.options

import android.content.ContentResolver
import android.net.Uri
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.vladder2312.studentdocs.App
import com.vladder2312.studentdocs.data.repositories.DocRepository
import com.vladder2312.studentdocs.utils.MemoryChecker
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Презентер фрагмента настроек приложения
 */
@InjectViewState
class OptionsPresenter : MvpPresenter<OptionsView>() {

    @Inject
    lateinit var docRepository: DocRepository

    init {
        App.appComponent.inject(this)
    }

    val model = OptionsModel()

    fun loadData(contentResolver: ContentResolver) {
        val uriList = mutableListOf<Uri>()

        val disposable = docRepository.getAllPhotos()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { list ->
                list.map {
                    uriList.add(Uri.parse(it.uri))
                }
                model.memoryUsed = MemoryChecker.calculateUsedMemory(contentResolver, uriList)
                model.amountPhoto = uriList.size
                viewState.showUsage(model.memoryUsed, model.amountPhoto)
            }
    }

    fun deleteData(){
        docRepository.deleteAll()
    }

    fun makeCopy(){

    }

    fun loadCopy(){

    }
}
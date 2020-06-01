package com.vladder2312.studentdocs.data.repositories

import com.vladder2312.studentdocs.data.db.DocDao
import com.vladder2312.studentdocs.domain.Document
import com.vladder2312.studentdocs.domain.Photo
import io.reactivex.Flowable
import javax.inject.Inject

class DocRepository @Inject constructor(
    val docDao: DocDao
) {

    fun getDocuments() = docDao.getDocuments().map { list ->
        list.map {
            it.transform()
        }
    }

    fun getDocumentsByCategory(category: String) =
        docDao.getDocumentsByCategory(category).map { list ->
            list.map {
                it.transform()
            }
        }

    fun getDocumentsByName(name: String) = docDao.getDocumentsByName(name).map { list ->
        list.map {
            it.transform()
        }
    }

    fun getPhotos(documentId: String) = docDao.getPhotos(documentId).map { list ->
        list.map {
            it.transform()
        }
    }

    fun insertDocument(document: Document) = docDao.insertDocument(document.transform())

    fun insertPhoto(photo: Photo) = docDao.insertPhoto(photo.transform())

    fun deletePhoto(id: String) = docDao.deletePhoto(id)

    fun deleteDocument(id: String) = docDao.deleteDocument(id)
}
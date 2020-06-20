package com.vladder2312.studentdocs.data.repositories

import com.vladder2312.studentdocs.data.db.DocDao
import com.vladder2312.studentdocs.domain.Document
import com.vladder2312.studentdocs.domain.Photo
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Репозиторий документов
 */
class DocRepository @Inject constructor(
    private val docDao: DocDao
) {

    fun getDocuments(): Flowable<List<Document>> = docDao.getDocuments().map { list ->
        list.map {
            it.transform()
        }
    }

    fun getPhotos(documentId: String): Flowable<List<Photo>> =
        docDao.getPhotos(documentId).map { list ->
            list.map {
                it.transform()
            }
        }

    fun getAllPhotos(): Flowable<List<Photo>> = docDao.getAllPhotos().map { list ->
        list.map {
            it.transform()
        }
    }

    fun insertDocument(document: Document) = docDao.insertDocument(document.transform())

    fun insertPhoto(photo: Photo) = docDao.insertPhoto(photo.transform())

    fun deletePhoto(id: String) = docDao.deletePhoto(id)

    fun deleteDocument(id: String) {
        docDao.deleteDocument(id)
        docDao.deletePhotoOfDocuments(id)
    }

    fun deleteAll() {
        docDao.deleteAllDocuments()
        docDao.deleteAllPhotos()
    }

    fun updateDocument(document: Document) = docDao.updateDocument(document.transform())
}
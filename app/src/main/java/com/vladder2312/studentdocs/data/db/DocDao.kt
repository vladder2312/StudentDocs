package com.vladder2312.studentdocs.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.vladder2312.studentdocs.data.db.entities.DocumentEntity
import com.vladder2312.studentdocs.data.db.entities.PhotoEntity
import io.reactivex.Flowable

@Dao
interface DocDao {

    @Query("Select * From DocumentEntity")
    fun getDocuments() : Flowable<List<DocumentEntity>>

    @Query("Select * From DocumentEntity Where category=:category")
    fun getDocumentsByCategory(category : String) : Flowable<List<DocumentEntity>>

    @Query("Select * From DocumentEntity Where name like :name")
    fun getDocumentsByName(name: String) : Flowable<List<DocumentEntity>>

    @Query("Select * From PhotoEntity Where documentId=:documentId")
    fun getPhotos(documentId : String) : Flowable<List<PhotoEntity>>

    @Query("Delete From DocumentEntity Where id = :id")
    fun deleteDocument(id : String)

    @Query("Delete From PhotoEntity Where id = :id")
    fun deletePhoto(id : String)

    @Insert
    fun insertDocument(documentEntity: DocumentEntity)

    @Insert
    fun insertPhoto(photoEntity: PhotoEntity)
}
package com.vladder2312.studentdocs.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.vladder2312.studentdocs.data.db.entities.DocumentEntity
import com.vladder2312.studentdocs.data.db.entities.PhotoEntity
import io.reactivex.Flowable

@Dao
interface DocDao {

    @Query("Select * From DocumentEntity")
    fun getDocuments(): Flowable<List<DocumentEntity>>

    @Query("Select * From PhotoEntity Where documentId=:documentId")
    fun getPhotos(documentId: String): Flowable<List<PhotoEntity>>

    @Query("Select * From PhotoEntity")
    fun getAllPhotos(): Flowable<List<PhotoEntity>>

    @Query("Delete From DocumentEntity Where id = :id")
    fun deleteDocument(id: String)

    @Query("Delete From PhotoEntity Where id = :id")
    fun deletePhoto(id: String)

    @Query("Delete From PhotoEntity")
    fun deleteAllPhotos()

    @Query("Delete From DocumentEntity")
    fun deleteAllDocuments()

    @Insert
    fun insertDocument(documentEntity: DocumentEntity)

    @Insert
    fun insertPhoto(photoEntity: PhotoEntity)

    @Update
    fun updateDocument(documentEntity: DocumentEntity)
}
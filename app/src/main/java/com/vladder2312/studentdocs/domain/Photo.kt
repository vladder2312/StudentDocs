package com.vladder2312.studentdocs.domain

import com.vladder2312.studentdocs.data.db.entities.PhotoEntity

/**
 * Доменная модель фото
 */
data class Photo(
    val id : String,
    val documentId : String,
    val uri : String
) {

    fun transform(): PhotoEntity {
        return PhotoEntity(id, documentId, uri)
    }
}
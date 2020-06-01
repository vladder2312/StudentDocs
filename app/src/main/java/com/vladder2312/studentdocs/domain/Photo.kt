package com.vladder2312.studentdocs.domain

import com.vladder2312.studentdocs.data.db.entities.PhotoEntity

data class Photo(
    val id : String,
    val documentId : String,
    val utl : String
) {

    fun transform(): PhotoEntity {
        return PhotoEntity(id, documentId, utl)
    }
}
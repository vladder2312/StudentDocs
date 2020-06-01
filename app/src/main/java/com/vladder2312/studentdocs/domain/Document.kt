package com.vladder2312.studentdocs.domain

import com.vladder2312.studentdocs.data.db.entities.DocumentEntity
import java.io.Serializable

data class Document(
    val id : String,
    val name : String,
    val category : String,
    val createdDate : Long
) : Serializable {

    fun transform(): DocumentEntity {
        return DocumentEntity(id, name, category, createdDate)
    }
}
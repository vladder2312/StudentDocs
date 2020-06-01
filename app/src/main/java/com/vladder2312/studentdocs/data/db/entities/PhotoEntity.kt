package com.vladder2312.studentdocs.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vladder2312.studentdocs.domain.Photo

@Entity
data class PhotoEntity(
    @PrimaryKey var id : String,
    @ColumnInfo(name = "documentId") var documentId : String,
    @ColumnInfo(name = "utl") var utl : String
) {

    fun transform() : Photo {
        return Photo(id, documentId, utl)
    }
}
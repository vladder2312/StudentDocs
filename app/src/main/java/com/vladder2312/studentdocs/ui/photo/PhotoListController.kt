package com.vladder2312.studentdocs.ui.photo

import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import com.squareup.picasso.Picasso
import com.vladder2312.studentdocs.R
import com.vladder2312.studentdocs.domain.Photo
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder

class PhotoListController(
    private val onClickPhotoListener: (Photo) -> Unit
) : BindableItemController<Photo, PhotoListController.PhotoListHolder>() {

    inner class PhotoListHolder(parent: ViewGroup) :
        BindableViewHolder<Photo>(parent, R.layout.item_photo) {
        private val card: CardView = itemView.findViewById(R.id.photo_item)
        private val image: ImageView = itemView.findViewById(R.id.image_photo)
        private lateinit var photo: Photo

        init {
            card.setOnClickListener { onClickPhotoListener(photo) }
        }

        override fun bind(data: Photo) {
            this.photo = data
            Picasso.get().load(data.uri).into(image)
        }
    }

    override fun getItemId(photo: Photo) = photo.id

    override fun createViewHolder(parent: ViewGroup) = PhotoListHolder(parent)
}
package com.example.webapp.ui.retrofit.shared.interfaces

import com.example.webapp.data.model.PhotosItem
import com.example.webapp.data.model.Post

interface IPhoto {
    fun onCellClickListener(photosItem: PhotosItem)
}
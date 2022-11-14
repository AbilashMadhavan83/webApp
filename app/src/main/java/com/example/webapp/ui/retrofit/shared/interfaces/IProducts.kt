package com.example.webapp.ui.retrofit.shared.interfaces

import com.example.webapp.data.model.PhotosItem
import com.example.webapp.data.model.productsItem

interface IProducts {
    fun onCellClickListener(photosItem: productsItem)
}
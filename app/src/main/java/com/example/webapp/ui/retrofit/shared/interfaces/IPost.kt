package com.example.webapp.ui.retrofit.shared.interfaces

import com.example.webapp.data.model.Post

interface IPost {
    fun onCellClickListener(post: Post)
}
package com.example.webapp.ui.retrofit.shared.interfaces

import com.example.webapp.data.model.Post
import com.example.webapp.data.model.Todo

interface ITodo {
    fun onCellClickListener(post: Todo)
}
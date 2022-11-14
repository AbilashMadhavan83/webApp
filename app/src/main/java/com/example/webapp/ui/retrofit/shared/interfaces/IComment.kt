package com.example.webapp.ui.retrofit.shared.interfaces

import com.example.webapp.data.model.Comment

interface IComment {
    fun onCellClickListener(comment: Comment)
}
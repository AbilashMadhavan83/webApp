package com.example.webapp.data.repository

import com.example.webapp.data.model.*
import com.example.webapp.data.network.remote.api.IApiSecondURL
import com.example.webapp.data.network.remote.api.IApiService
import retrofit2.Call


class AppRepository(private val iApi: IApiService, private val apiSecondURL: IApiSecondURL) {

    fun getPosts(): Call<List<Post>> =  iApi.getPosts()
    fun getPost(id: Int): Call<Post> = iApi.getPost(id)
    fun getComments(): Call<List<Comment>> =  iApi.getComments()
    fun searchComments(postId: String?, id: String?): Call<List<Comment>> = iApi.searchComments(postId,id)
    fun searchTodos(filter: HashMap<String, String>):Call<List<Todo>> = iApi.searchTodos(filter)
    fun getTodos(): Call<List<Todo>> =  iApi.getTodos()
    fun searchTodos(id: String?, userId: String?, completed: String? ):Call<Todo> = iApi.searchTodos(id,userId,completed)
    fun getPhotos(): Call<List<PhotosItem>> =  iApi.getPhotos()

    fun getProducts(url: String): Call<List<productsItem>> = apiSecondURL.getProducts(url)
    fun addPost(newPost: Post?): Call<Post> = iApi.addPost(newPost)
    fun patchPost(id: Int?, userId: Int?, title: String, body: String,): Call<Post> = iApi.updatePost(id = id, userId = userId, title = title, body = body)
    fun deletePost(id: Int?):Call<Unit> = iApi.deletePost(id = id)
    fun patchPost(id: Int?, post: Post) = iApi.patchPost (id = id, post = post)

}
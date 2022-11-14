package com.example.webapp.data.network.remote.api

import com.example.webapp.data.model.Comment
import com.example.webapp.data.model.PhotosItem
import com.example.webapp.data.model.Post
import com.example.webapp.data.model.Todo
import retrofit2.Call
import retrofit2.http.*
import java.util.logging.Filter


interface IApiService {

    //retrieve data
    //https://jsonplaceholder.typicode.com/posts
    @GET("posts")
    fun getPosts(): Call<List<Post>>

    @GET("photos")
    fun getPhotos(): Call<List<PhotosItem>>

    //Path parameter
    //https://jsonplaceholder.typicode.com/posts/1
    @GET("posts/{id}")
    fun getPost(@Path("id") id:Int): Call<Post>

    @GET("comments")
    fun getComments(): Call<List<Comment>>

    //https://jsonplaceholder.typicode.com/todos
    @GET("todos")
    fun getTodos(): Call<List<Todo>>

    //Query Parameter
    //https://jsonplaceholder.typicode.com/comments?postId=4&&id=16
    @GET("comments")
    fun searchComments(@Query ("postId") postId:String?,@Query ("id") id:String?): Call<List<Comment>>

    //Query Map
    // https://jsonplaceholder.typicode.com/todos?userId=1&&completed=true&&id=8
    @GET("todos")
    fun searchTodos(@QueryMap filter: HashMap<String,String>): Call<List<Todo>>

    //Multiple Query Parameters (Path parameter with Query Parameter)
    // https://jsonplaceholder.typicode.com/todos/4?userId=1&&completed=true
    @GET("todos/{id}")
    fun searchTodos(@Path("id") id:String?, @Query ("userId") userId:String?,@Query ("completed") completed:String?): Call<Todo>


    /**
     * CRUD Operation
     */

    /**
     * Json Format
     */
    @POST("posts")
    fun addPost(@Body newPost: Post?):Call<Post>

    /**
     * Form Url Encoded Format
     */

    @FormUrlEncoded
    @PUT("posts/{id}")
    fun updatePost(
        @Path("id") id:Int?,
        @Field("userId") userId:Int?,
        @Field("title") title:String,
        @Field("body") body:String
    ):Call<Post>

    /**
     * Delete
     */

    @DELETE("posts/{id}")
    fun deletePost(@Path("id") id:Int?):Call<Unit>

    /**
     * Patch
     */
    @PATCH("posts/{id}")
    fun patchPost(@Path("id") id:Int?,@Body post: Post):Call<Post>
}
package com.example.webapp.ui.retrofit.shared.viewModel
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.webapp.data.model.*
import com.example.webapp.data.repository.AppRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SharedViewModel(
    private val repository: AppRepository
) : ViewModel() {
/*
    HTTP response status codes
    HTTP response status codes indicate whether a specific HTTP request has been successfully completed. Responses are grouped in five classes:

    Informational responses (100–199)
    Successful responses (200–299)
    Redirection messages (300–399)
    Client error responses (400–499)
    Server error responses (500–599)

 */



    //Posts List list
    private val _posts = MutableLiveData<List<Post>?>()
    val posts: LiveData<List<Post>?> = _posts

    //Posts List list
    private val _photos = MutableLiveData<List<PhotosItem>?>()
    val photos: LiveData<List<PhotosItem>?> = _photos

    //Posts List list
    private val _products = MutableLiveData<List<productsItem>?>()
    val products: LiveData<List<productsItem>?> = _products



    //comments List list
    private val _comments = MutableLiveData<List<Comment>?>()
    val comments: LiveData<List<Comment>?> = _comments

    private val _userId = MutableLiveData<Array<String>>()
    val userId: LiveData<Array<String>> = _userId

    private val _todos = MutableLiveData<List<Todo>?>()
    val todos: LiveData<List<Todo>?> = _todos
    fun todoList(): LiveData<List<Todo>?> = searchTodos()
    fun todoLst(): LiveData<List<Todo>?> = searchTodosMultiple()



    var _postId = MutableLiveData<String>()





    val _id = MutableLiveData<String>()
//
//    val _toDo_Completed = MutableLiveData<Boolean>()


//    private val _response = MutableLiveData<String?>()
//    val response: LiveData<String?>
//        get() = _response

    private val _response = MutableLiveData<String?>()
    fun response(): LiveData<String?> = _response

    fun commentsList(): LiveData<List<Comment>?> = searchComments()


    var _toDoUserIdPosition = MutableLiveData<Int>()
    private val _toDoUserIdLst = MutableLiveData<Array<String>>()
    val toDoUserIdLst : LiveData<Array<String>> = _toDoUserIdLst
    private val _toDoUserId = MutableLiveData<String>()

    var _toDoIdPosition = MutableLiveData<Int>()
    private val _toDoIdLst = MutableLiveData<Array<String>>()
    val toDoIdLst : LiveData<Array<String>> = _toDoIdLst
    private val _toDoId = MutableLiveData<String>()

    var _toDoCompletedPosition = MutableLiveData<Int>()
    private val _toDoCompletedLst = MutableLiveData<Array<String>>()
    val toDoCompletedLst : LiveData<Array<String>> = _toDoCompletedLst
    private val _toDoCompleted = MutableLiveData<String>()


    val Title = MutableLiveData<String?>()
    val Body = MutableLiveData<String?>()
    val UserID = MutableLiveData<Int?>()
    val responsePost = MutableLiveData<String?>()

    init {
        getPosts()
        getComments()
        getTodos()
        getPhotos()
        getProducts()
        deletePost()
    }

    fun patchPost() {
        val post = Post(
            body = "",
            id = 1,
            title = "",
            userId = 1
        )
        val data = repository.patchPost (id = 1, post = post)
        data.enqueue(object : Callback<Post?> {
            override fun onResponse(call: Call<Post?>, response: Response<Post?>) {
                val responseBody = response.body()
                if (response.isSuccessful) {
                    //status code range 100"s
                    if (responseBody != null) {

//                        responsePost.value = "New Post: " +
//                                "\nid: " + responseBody.id +
//                                ",\nuserId: " + responseBody.userId +
//                                ",\nbody: " + responseBody.body +
//                                ",\ntitle: " + responseBody.title
//
//                        //finish()

                        Log.d(TAG, "Patch Post: " + "Success")

                        Log.d(
                            TAG, "\nPatch Post:- " +
                                    "\nid:" + responseBody.id +
                                    "\nuserId:" + responseBody.userId+
                                    "\ntitle:" + responseBody.title +
                                    "\nbody:" + responseBody.body
                        )
                    }
                }
                else{
                    Log.d(TAG, "Patch Post: " + "Failed")
                }
            }

            override fun onFailure(call: Call<Post?>, t: Throwable) {
                Log.d(TAG, "Patch Post: " + t.message)
            }
        })

    }

    fun deletePost() {
        val data = repository.deletePost(id = 1)
        data.enqueue(object : Callback<Unit?> {
            override fun onResponse(call: Call<Unit?>, response: Response<Unit?>) {
                if (response.isSuccessful) {
                    Log.d(TAG, "Delete Post: " + "Success")
                }
                else{
                    Log.d(TAG, "Delete Post: " + "Failed")
                }
            }

            override fun onFailure(call: Call<Unit?>, t: Throwable) {
                Log.d(TAG, "Delete Post: " + t.message)
            }
        })
    }

    fun updatePost(){

        val data = repository.patchPost(id = 2, userId = 1, title = "Title.value.toString()", body = "Body.value.toString()")
        data.enqueue(object : Callback<Post?> {
            override fun onResponse(call: Call<Post?>, response: Response<Post?>) {
                val responseBody = response.body()
                if (response.isSuccessful) {
                    //status code range 100"s
                    if (responseBody != null) {

//                        responsePost.value = "New Post: " +
//                                "\nid: " + responseBody.id +
//                                ",\nuserId: " + responseBody.userId +
//                                ",\nbody: " + responseBody.body +
//                                ",\ntitle: " + responseBody.title
//
//                        //finish()

                        Log.d(TAG, "Update Post: " + "Success")

                        Log.d(
                            TAG, "\nUpdate Post:- " +
                                    "\nid:" + responseBody.id +
                                    "\nuserId:" + responseBody.userId+
                                    "\ntitle:" + responseBody.title +
                                    "\nbody:" + responseBody.body
                        )
                    }
                }
                else{
                    Log.d(TAG, "Update Post: " + "Failed")
                }

            }

            override fun onFailure(call: Call<Post?>, t: Throwable) {
                Log.d(TAG, "Update Post: " + t.message)
            }
        })



    }

    fun AddPost() {

        val newPost = UserID.value?.let {
            Post(
                body = Body.value.toString(),
                id = 0,
                title = Title.value.toString(),
                userId = it
            )
        }

        Log.d(TAG, "Post: " + newPost?.body +
                "," + newPost?.id +
                "," + newPost?.title +
                "," + newPost?.userId
        )

        val data = repository.addPost(newPost)
        Title.value =""
        Body.value =""

        data.enqueue(object : Callback<Post?> {
            override fun onResponse(call: Call<Post?>, response: Response<Post?>) {
                val responseBody = response.body()
                if (response.isSuccessful) {
                    //status code range 100"s
                    if (responseBody != null) {

                        responsePost.value = "New Post: " +
                                "\nid: " + responseBody.id +
                                ",\nuserId: " + responseBody.userId +
                                ",\nbody: " + responseBody.body +
                                ",\ntitle: " + responseBody.title

                        //finish()
                        Log.d(TAG, "Add Post: " + "Success")

//                        Log.d(TAG, "New Post: " + responseBody.body +
//                                "," + responseBody.id +
//                                "," + responseBody.title +
//                                "," + responseBody.userId
//                        )
                    }
                }
                else{
                    Log.d(TAG, "Add Post: " + "Failed")
                }
            }

            override fun onFailure(call: Call<Post?>, t: Throwable) {
                Log.d(TAG, "Add Post: " + t.message)
            }
        })
    }

    private fun getProducts() {
        val URL: String = "https://fakestoreapi.com/products"
        val data  = repository.getProducts(URL)

        data.enqueue(object : Callback<List<productsItem>?> {
            override fun onResponse(
                call: Call<List<productsItem>?>,
                response: Response<List<productsItem>?>
            ) {
                val responseBody = response.body()
                if (response.isSuccessful) {
                    //status code range 100"s
                    if (responseBody != null) {
                        _products.value = responseBody
                    }
                }
            }

            override fun onFailure(call: Call<List<productsItem>?>, t: Throwable) {
                Log.d(TAG, "on failure: " + t.message)
            }
        })

    }

    private fun getPhotos() {

        val data  = repository.getPhotos()
        data.enqueue(object : Callback<List<PhotosItem>?> {
            override fun onResponse(
                call: Call<List<PhotosItem>?>,
                response: Response<List<PhotosItem>?>
            ) {
                val responseBody = response.body()
                if (response.isSuccessful) {
                    //status code range 100"s
                    if (responseBody != null) {
                        _photos.value = responseBody
                    }
                }
            }
            override fun onFailure(call: Call<List<PhotosItem>?>, t: Throwable) {
                Log.d(TAG, "on failure: " + t.message)
            }
        })
    }

    private fun searchTodosMultiple(): LiveData<List<Todo>?> {


        val _todosLst = MutableLiveData<List<Todo>?>()

        val data  = repository.searchTodos(_toDoId.value.toString(),_toDoUserId.value.toString(),_toDoCompleted.value.toString())


        data.enqueue(object : Callback<Todo?> {
            override fun onResponse(call: Call<Todo?>, response: Response<Todo?>) {
                val responseBody= response.body()
                if (response.isSuccessful) {
                    //status code range 100"s
                    if (responseBody != null) {
                        val todo: Todo = responseBody
                        _todosLst.value = listOf(todo)
                    }

                }
            }

            override fun onFailure(call: Call<Todo?>, t: Throwable) {
                Log.d(TAG, "on failure: " + t.message)
            }
        })


        return _todosLst

    }
    fun SetCompleted() {


        _toDoCompleted.value = _toDoCompletedLst.value!![_toDoCompletedPosition.value!!].toString()
        Log.d(TAG, "Completed :  ${_toDoCompleted.value}")
    }

    fun UpdateToDoCompleted() {


        _toDoId.value = _toDoIdLst.value!![_toDoIdPosition.value!!].toString()

        Log.d(TAG, "Id :  ${_toDoId.value}")

        val toDoCompleted: Array<String>   = _todos.value?.filter { it -> it.id ==  _toDoId.value!!.toInt() }
            ?.distinctBy { it.completed }
            ?.map { it.completed.toString() }!!.toTypedArray()
        _toDoCompletedLst.value  =  toDoCompleted

    }

    fun UpdateToDoIdLst(){

        _toDoUserId.value = _toDoUserIdLst.value!![_toDoUserIdPosition.value!!].toString()

        Log.d(TAG, "UserID :  ${_toDoUserId.value}")

        val toDoId: Array<String>   = _todos.value?.filter { it -> it.userId ==  _toDoUserId.value!!.toInt() }
            ?.distinctBy { it.id }
            ?.map { it.id.toString() }!!.toTypedArray()

        _toDoIdLst.value = toDoId
    }



    private fun getTodos(){

        val data  = repository.getTodos()
        data.enqueue(object : Callback<List<Todo>?> {
            override fun onResponse(call: Call<List<Todo>?>, response: Response<List<Todo>?>) {
                val responseBody= response.body()
                if (responseBody != null) {
                    _todos.value = responseBody
                    _toDoUserIdLst.value = _todos.value?.distinctBy { it.userId }?.map { it.userId.toString() }!!.toTypedArray()
                }
            }

            override fun onFailure(call: Call<List<Todo>?>, t: Throwable) {
                Log.d(TAG, "on failure: " + t.message)
            }
        })

    }



    private fun searchTodos(): MutableLiveData<List<Todo>?>{


        val filter = HashMap<String,String>()
        filter["userId"] = _toDoUserId.value.toString()
        filter["completed"] = _toDoCompleted.value.toString()
        filter["id"] = _toDoId.value.toString()

        val _todosLst = MutableLiveData<List<Todo>?>()

        val data  = repository.searchTodos(filter)

        data.enqueue(object : Callback<List<Todo>?> {
            override fun onResponse(call: Call<List<Todo>?>, response: Response<List<Todo>?>) {
                val responseBody= response.body()
                if (responseBody != null) {
                    _todosLst.value = responseBody
                }
            }

            override fun onFailure(call: Call<List<Todo>?>, t: Throwable) {
                Log.d(TAG, "on failure: " + t.message)
            }
        })

        return _todosLst

    }

    private fun searchComments(): MutableLiveData<List<Comment>?>{

        val _commentsList = MutableLiveData<List<Comment>?>()
        val data  = repository.searchComments(_postId.value,_id.value)
        data.enqueue(object : Callback<List<Comment>?> {
            override fun onResponse(
                call: Call<List<Comment>?>,
                response: Response<List<Comment>?>
            ) {
                val responseBody= response.body()
                if (responseBody != null) {
                    _commentsList.value = responseBody
                }
            }

            override fun onFailure(call: Call<List<Comment>?>, t: Throwable) {
                Log.d(TAG, "on failure: " + t.message)
            }
        })

        return _commentsList
    }

    private fun getComments() {

        val data  = repository.getComments()

        data.enqueue(object : Callback<List<Comment>?> {
            override fun onResponse(
                call: Call<List<Comment>?>,
                response: Response<List<Comment>?>
            ) {
                val responseBody= response.body()
                if (responseBody != null) {
                    _comments.value = responseBody
                }
            }

            override fun onFailure(call: Call<List<Comment>?>, t: Throwable) {
                Log.d(TAG, "on failure: " + t.message)
            }
        })
    }

    private fun getPosts(){

        val data  = repository.getPosts()

        data.enqueue(object : Callback<List<Post>?> {
            override fun onResponse(call: Call<List<Post>?>, response: Response<List<Post>?>) {
                val responseBody= response.body()
                if (responseBody != null) {
                    _posts.value = responseBody
                    _userId.value = _posts.value?.distinctBy { it.userId }?.map { it.userId.toString() }?.toTypedArray()
                    //_todos.value?.distinctBy { it.userId }?.map { it.userId.toString() }!!.toTypedArray()
                }
            }

            override fun onFailure(call: Call<List<Post>?>, t: Throwable) {
                Log.d(TAG, "on failure: " + t.message)
            }
        })
    }


    fun getPost(): LiveData<List<Post>?>{

        return loadPost()
    }

    private fun loadPost(): MutableLiveData<List<Post>?> {

        //Post
        val _post = MutableLiveData<List<Post>?>()

        val data = _postId.value?.toInt()?.let { repository.getPost(it) }

        // Shift + Ctrl + Space
        data?.enqueue(object : Callback<Post?> {
            override fun onResponse(call: Call<Post?>, response: Response<Post?>) {
                val responseBody = response.body()


                if (response.isSuccessful) {
                    //status code range 100"s
                    if (responseBody != null) {
                        val post: Post = responseBody
                        _post.value = listOf(post)

                    }

                }
                else {
                    //status code range 300's,400's and 500's
                    val statusCode = response.code()
                    when(statusCode){
                        404 -> _response.value = "Not Found $statusCode"
                    }
                }

            }

            override fun onFailure(call: Call<Post?>, t: Throwable) {

                Log.d(TAG, "on failure: " + t.message)
            }
        })
        return _post
    }





    fun responseStatus() {
        _response.value = null
    }




}
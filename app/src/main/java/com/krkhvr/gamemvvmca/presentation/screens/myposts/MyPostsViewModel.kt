package com.krkhvr.gamemvvmca.presentation.screens.myposts

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krkhvr.gamemvvmca.domain.model.Post
import com.krkhvr.gamemvvmca.domain.model.Response
import com.krkhvr.gamemvvmca.domain.usecases.auth.AuthUseCases
import com.krkhvr.gamemvvmca.domain.usecases.posts.PostsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPostsViewModel @Inject constructor(
    private val postsUseCase: PostsUseCases,
    private val authUseCases: AuthUseCases
) : ViewModel() {

    var postRespose by mutableStateOf<Response<List<Post>>?>(null)
    var deleteRespose by mutableStateOf<Response<Boolean>?>(null)

    init {
        getPosts()
    }

    fun getPosts() = viewModelScope.launch {
        postRespose = Response.Loading
        postsUseCase.getPostsByUserId(authUseCases.getCurrentUser()?.uid ?: "").collect(){
            postRespose = it
        }
    }

    fun deletePost(postId: String) = viewModelScope.launch {
        deleteRespose = Response.Loading
        deleteRespose = postsUseCase.deletePost(postId)
    }
}
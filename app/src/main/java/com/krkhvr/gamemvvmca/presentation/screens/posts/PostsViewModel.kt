package com.krkhvr.gamemvvmca.presentation.screens.posts

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
class PostsViewModel @Inject constructor(
    private val postsUseCase: PostsUseCases,
    private val authUseCases: AuthUseCases,
) : ViewModel() {

    var postRespose by mutableStateOf<Response<List<Post>>?>(null)
        private set

    var likePostResponse by mutableStateOf<Response<Boolean>?>(null)
        private set

    var dislikePostResponse by mutableStateOf<Response<Boolean>?>(null)
        private set

    val currentUser = authUseCases.getCurrentUser()

    init {
        getPosts()
    }

    fun getPosts() = viewModelScope.launch {
        postRespose = Response.Loading
        postsUseCase.getPosts().collect(){
            postRespose = it
        }
    }

    fun likePost(postId: String, userId: String) = viewModelScope.launch {
        likePostResponse = Response.Loading
        likePostResponse = postsUseCase.like(postId, userId)
    }

    fun dislikePost(postId: String, userId: String) = viewModelScope.launch {
        dislikePostResponse = Response.Loading
        dislikePostResponse = postsUseCase.dislike(postId, userId)
    }
}
package com.krkhvr.gamemvvmca.presentation.screens.postdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.krkhvr.gamemvvmca.domain.model.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    val post: Post =  Post.fromJson(savedStateHandle.get<String>("post")!!)
}
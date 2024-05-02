package com.krkhvr.gamemvvmca.domain.repository

import com.krkhvr.gamemvvmca.domain.model.Post
import com.krkhvr.gamemvvmca.domain.model.Response
import kotlinx.coroutines.flow.Flow
import java.io.File

interface PostRepository {

    suspend fun createPost(post: Post, file: File): Response<Boolean>
    suspend fun deletePost(postId: String): Response<Boolean>
    suspend fun updatePost(post: Post, file: File?): Response<Boolean>
    suspend fun like(postId: String, userId: String): Response<Boolean>
    suspend fun dislike(postId: String, userId: String): Response<Boolean>
    fun getPosts(): Flow<Response<List<Post>>>
    fun getPostsByUserId(userId: String): Flow<Response<List<Post>>>
}
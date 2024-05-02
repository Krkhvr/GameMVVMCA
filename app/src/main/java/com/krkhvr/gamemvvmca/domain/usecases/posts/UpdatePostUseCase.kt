package com.krkhvr.gamemvvmca.domain.usecases.posts

import com.krkhvr.gamemvvmca.domain.model.Post
import com.krkhvr.gamemvvmca.domain.repository.PostRepository
import java.io.File
import javax.inject.Inject

class UpdatePostUseCase @Inject constructor(
    private val repository: PostRepository
) {

    suspend operator fun invoke(post: Post, file: File?) = repository.updatePost(post, file)
}
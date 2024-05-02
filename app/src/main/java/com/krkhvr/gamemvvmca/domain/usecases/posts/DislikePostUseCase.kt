package com.krkhvr.gamemvvmca.domain.usecases.posts

import com.krkhvr.gamemvvmca.domain.model.Post
import com.krkhvr.gamemvvmca.domain.repository.PostRepository
import java.io.File
import javax.inject.Inject

class DislikePostUseCase @Inject constructor(
    private val repository: PostRepository
) {

    suspend operator fun invoke(postId: String, userId: String) = repository.dislike(postId, userId)
}
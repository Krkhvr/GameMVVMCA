package com.krkhvr.gamemvvmca.domain.usecases.posts

import com.krkhvr.gamemvvmca.domain.repository.PostRepository
import javax.inject.Inject

class GetPostsByUserIdUseCase @Inject constructor(
    private val postRepository: PostRepository
) {
    operator fun invoke(userId: String) = postRepository.getPostsByUserId(userId)
}
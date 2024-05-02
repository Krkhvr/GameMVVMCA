package com.krkhvr.gamemvvmca.domain.usecases.posts

import com.krkhvr.gamemvvmca.domain.repository.PostRepository
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val postRepository: PostRepository
) {
    operator fun invoke() = postRepository.getPosts()
}
package com.krkhvr.gamemvvmca.domain.usecases.posts

data class PostsUseCases(
    val createPost: CreatePostUseCase,
    val getPosts: GetPostsUseCase,
    val getPostsByUserId: GetPostsByUserIdUseCase,
    val deletePost: DeletePostUseCase,
    val updatePost: UpdatePostUseCase,
    val like: LikePostUseCase,
    val dislike: DislikePostUseCase
)
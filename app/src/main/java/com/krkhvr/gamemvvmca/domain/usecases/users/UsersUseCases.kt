package com.krkhvr.gamemvvmca.domain.usecases.users

data class UsersUseCases(
    val create: CreateUseCase,
    val getUserById: GetUserByIdUseCase,
    val update: UpdateUseCase,
    val saveImage: SaveImageUseCase
)

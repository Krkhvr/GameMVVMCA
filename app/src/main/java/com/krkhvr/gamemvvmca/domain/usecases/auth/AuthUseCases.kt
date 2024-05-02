package com.krkhvr.gamemvvmca.domain.usecases.auth

data class AuthUseCases(
    val getCurrentUser: GetCurrentUserUseCase,
    val login: LoginUseCase,
    val signUp: SignUpUseCase,
    val logout: LogoutUseCase
)

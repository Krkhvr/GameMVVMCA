package com.krkhvr.gamemvvmca.domain.usecases.auth

import com.krkhvr.gamemvvmca.domain.model.User
import com.krkhvr.gamemvvmca.domain.repository.AuthRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val repository: AuthRepository) {

    suspend operator fun invoke(user: User) = repository.signUp(user)

}
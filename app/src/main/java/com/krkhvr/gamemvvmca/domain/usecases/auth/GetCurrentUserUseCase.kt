package com.krkhvr.gamemvvmca.domain.usecases.auth

import com.krkhvr.gamemvvmca.domain.repository.AuthRepository
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(private val repository: AuthRepository) {

    operator fun invoke() = repository.currentUser
}
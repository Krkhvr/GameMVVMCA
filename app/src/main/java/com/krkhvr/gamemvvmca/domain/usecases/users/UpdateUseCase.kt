package com.krkhvr.gamemvvmca.domain.usecases.users

import com.krkhvr.gamemvvmca.domain.model.User
import com.krkhvr.gamemvvmca.domain.repository.UsersRepository
import javax.inject.Inject

class UpdateUseCase @Inject constructor(private val repository: UsersRepository) {

    suspend operator fun invoke(user: User) = repository.update(user)
}
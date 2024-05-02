package com.krkhvr.gamemvvmca.domain.usecases.users

import com.krkhvr.gamemvvmca.domain.repository.UsersRepository
import javax.inject.Inject

class GetUserByIdUseCase @Inject constructor(private val repository: UsersRepository){

    operator fun invoke(id: String) = repository.getUserById(id)
}
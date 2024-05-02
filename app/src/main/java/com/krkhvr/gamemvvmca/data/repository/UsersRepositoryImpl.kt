package com.krkhvr.gamemvvmca.data.repository

import android.net.Uri
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.storage.StorageReference
import com.krkhvr.gamemvvmca.core.Constants.USERS
import com.krkhvr.gamemvvmca.domain.model.Response
import com.krkhvr.gamemvvmca.domain.model.User
import com.krkhvr.gamemvvmca.domain.repository.UsersRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.io.File
import javax.inject.Inject
import javax.inject.Named

class UsersRepositoryImpl @Inject constructor(
    @Named(USERS) private val usersRef: CollectionReference,
    @Named(USERS) private val storageUsersRef: StorageReference
) : UsersRepository
{

    override suspend fun create(user: User): Response<Boolean> {
        return try{
            usersRef.document(user.id).set(user.apply { password = "" }).await().run {
                Response.Success(true)
            }
        }catch (e: Exception){
            Response.Failure(e)
        }
    }

    override suspend fun update(user: User): Response<Boolean> {
        return try{
            val map: MutableMap<String, Any> = HashMap()
            map["username"] = user.username
            map["image"] = user.image

            usersRef.document(user.id).update(map).await().run {
                Response.Success(true)
            }
        }catch (e: Exception){
            Response.Failure(e)
        }
    }

    override suspend fun saveImage(file: File): Response<String> {
        return try{
            val fromFile = Uri.fromFile(file)
            val ref = storageUsersRef.child(file.name)
            val uploadTask = ref.putFile(fromFile).await()
            val uri = ref.downloadUrl.await()
            return  Response.Success(uri.toString())
        }catch (e: Exception){
            Response.Failure(e)
        }
    }

    override fun getUserById(id: String): Flow<User> = callbackFlow {
        val snapshotListener = usersRef.document(id).addSnapshotListener { snapshot, error ->
            val user = snapshot?.toObject(User::class.java) ?: User()
            trySend(user)
        }

        awaitClose{
            snapshotListener.remove()
        }
    }

}
package com.krkhvr.gamemvvmca.data.repository

import android.net.Uri
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.storage.StorageReference
import com.krkhvr.gamemvvmca.core.Constants.POSTS
import com.krkhvr.gamemvvmca.core.Constants.USERS
import com.krkhvr.gamemvvmca.domain.model.Post
import com.krkhvr.gamemvvmca.domain.model.Response
import com.krkhvr.gamemvvmca.domain.model.User
import com.krkhvr.gamemvvmca.domain.repository.PostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.io.File
import javax.inject.Inject
import javax.inject.Named

class PostRepositoryImp @Inject constructor(
    @Named(USERS) private val usersRef: CollectionReference,
    @Named(POSTS) private val postsRef: CollectionReference,
    @Named(POSTS) private val storagePostsRef: StorageReference
) : PostRepository {

    override suspend fun createPost(post: Post, file: File): Response<Boolean> {
        return try {
            val fromFile = Uri.fromFile(file)
            val ref = storagePostsRef.child(file.name)
            val uploadTask = ref.putFile(fromFile).await()
            val uri = ref.downloadUrl.await()
            postsRef.add(post.apply { image = uri.toString() })
            return Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override fun getPosts(): Flow<Response<List<Post>>> = callbackFlow {
        val snapshotListener = postsRef.addSnapshotListener { value, error ->

            GlobalScope.launch(Dispatchers.IO) {
                val postResponse = if (value != null) {
                    val posts = value.toObjects(Post::class.java)
                    val userIdArray = ArrayList<String>()

                    value.documents.forEachIndexed { index, documentSnapshot ->
                        posts[index].id = documentSnapshot.id
                    }

                    posts.forEach {
                        userIdArray.add(it.userId)
                    }

                    userIdArray.toSet().toList().map { id ->
                        async {
                            val user =
                                usersRef.document(id).get().await().toObject(User::class.java)!!
                            posts.forEach { post ->
                                if (post.userId == id) {
                                    post.user = user
                                }
                            }
                        }
                    }.forEach {
                        it.await()
                    }
                    Response.Success(posts)
                } else {
                    Response.Failure(error)
                }
                trySend(postResponse)
            }
        }

        awaitClose {
            snapshotListener.remove()
        }
    }

    override fun getPostsByUserId(userId: String): Flow<Response<List<Post>>> = callbackFlow {
        val snapshotListener =
            postsRef.whereEqualTo("userId", userId).addSnapshotListener { value, error ->
                val postResponse = if (value != null) {
                    val posts = value.toObjects(Post::class.java)
                    value.documents.forEachIndexed { index, documentSnapshot ->
                        posts[index].id = documentSnapshot.id
                    }
                    Response.Success(posts)
                } else {
                    Response.Failure(error)
                }
                trySend(postResponse)
            }

        awaitClose {
            snapshotListener.remove()
        }
    }

    override suspend fun deletePost(postId: String): Response<Boolean> {
        return try {
            postsRef.document(postId).delete().await()
            return Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override suspend fun updatePost(post: Post, file: File?): Response<Boolean> {
        return try {
            if (file != null) {
                val fromFile = Uri.fromFile(file)
                val ref = storagePostsRef.child(file.name)
                val uploadTask = ref.putFile(fromFile).await()
                val uri = ref.downloadUrl.await()
                post.image = uri.toString()
            }

            postsRef.document(post.id).update(
                mapOf(
                    "name" to post.name,
                    "description" to post.description,
                    "image" to post.image,
                    "category" to post.category
                )
            ).await()
            return Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override suspend fun like(postId: String, userId: String): Response<Boolean> {
        return try {
            postsRef.document(postId).update("likes", FieldValue.arrayUnion(userId)).await()
            return Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override suspend fun dislike(postId: String, userId: String): Response<Boolean> {
        return try {
            postsRef.document(postId).update("likes", FieldValue.arrayRemove(userId)).await()
            return Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }
}
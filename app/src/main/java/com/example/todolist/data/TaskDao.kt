package com.example.todolist.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: TaskEntity)

    @Delete
    suspend fun delete(entity: TaskEntity)

    @Query("SELECT * FROM todos")
    fun getAll(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM todos WHERE id = :id")
    suspend fun getBy(id: Long): TaskEntity?


}
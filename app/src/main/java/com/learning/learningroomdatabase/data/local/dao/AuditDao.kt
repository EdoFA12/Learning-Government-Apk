package com.learning.learningroomdatabase.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.learning.learningroomdatabase.data.local.entity.AuditEntity

@Dao
interface AuditDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAudit(audit: AuditEntity)

    @Query("SELECT * FROM temuan_table ORDER BY id DESC")
    fun getAllAudits(): LiveData<List<AuditEntity>>

    @Delete
    suspend fun deleteAudit(audit: AuditEntity)

    @Query("DELETE FROM temuan_table")
    suspend fun deleteAllAudits()
}



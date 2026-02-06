package com.learning.learningroomdatabase.repository

import androidx.lifecycle.LiveData
import com.learning.learningroomdatabase.data.local.dao.AuditDao
import com.learning.learningroomdatabase.data.local.entity.AuditEntity

class AuditRepository(private val auditDao: AuditDao) {

    val AllAudit: LiveData<List<AuditEntity>> = auditDao.getAllAudits()

    suspend fun insertAudit(audit: AuditEntity) {
        auditDao.insertAudit(audit)
    }

    suspend fun deleteAudit(audit: AuditEntity) {
        auditDao.deleteAudit(audit)
    }
}
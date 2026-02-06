package com.learning.learningroomdatabase.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.learning.learningroomdatabase.data.local.entity.AuditEntity
import com.learning.learningroomdatabase.repository.AuditRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuditViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: AuditRepository
    val allAudits: LiveData<List<AuditEntity>>

    init {
        val auditDao = com.learning.learningroomdatabase.data.local.AppDatabase.getDatabase(application).auditDao()
        repository = AuditRepository(auditDao)
        allAudits = repository.AllAudit
    }

    fun insert (audit: AuditEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertAudit(audit)
    }

    fun delete(audit: AuditEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAudit(audit)
    }

}
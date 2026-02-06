package com.learning.learningroomdatabase.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.learning.learningroomdatabase.R

class AuditMainActivity : AppCompatActivity() {
    private lateinit var auditViewModel: AuditViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        auditViewModel = ViewModelProvider(this).get(AuditViewModel::class.java)

        auditViewModel.allAudits.observe(this){ listAudit ->
            Log.d("AuditMainActivity", "Number of audits: ${listAudit.size}")
        }
    }
}
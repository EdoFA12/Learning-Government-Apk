package com.learning.learningroomdatabase.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.learning.learningroomdatabase.R

class AuditMainActivity : AppCompatActivity() {
    private lateinit var auditViewModel: AuditViewModel
    private val binding: com.learning.learningroomdatabase.databinding.ActivityMainBinding by lazy {
        com.learning.learningroomdatabase.databinding.ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val view = binding.root
        setContentView(view)

        auditViewModel = ViewModelProvider(this).get(AuditViewModel::class.java)

        binding.btnSaveData.setOnClickListener {
            val nama_petugas = binding.etInputDataNama.text.toString().trim()
            val lokasi_temuan = binding.etInputDataLokasi.text.toString().trim()
            val status_prioritasi = binding.etInputDataStatus.text.toString().toIntOrNull() ?: 0


            val dataBaru = com.learning.learningroomdatabase.data.local.entity.AuditEntity(
                nama_petugas = nama_petugas,
                lokasi_temuan = lokasi_temuan,
                status_prioritasi = status_prioritasi
            )
            auditViewModel.insert(dataBaru)

            // Optional: clear inputs after save
            binding.etInputDataNama.text?.clear()
            binding.etInputDataLokasi.text?.clear()
            binding.etInputDataStatus.text?.clear()
        }

        auditViewModel.allAudits.observe(this){ listAudit ->
            Log.d("AuditMainActivity", "Number of audits: ${listAudit.size}")
            if (listAudit.isEmpty()) {
                binding.tvStatus.text = getString(R.string.no_data)
            } else {
                val formatted = listAudit.joinToString(separator = "\n") { audit ->
                    "- ${audit.nama_petugas} | ${audit.lokasi_temuan} | status: ${audit.status_prioritasi}"
                }
                binding.tvStatus.text = getString(R.string.audit_list_format, listAudit.size, formatted)
            }
        }


    }
}
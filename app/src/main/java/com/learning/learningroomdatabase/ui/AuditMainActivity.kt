package com.learning.learningroomdatabase.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
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

            binding.etInputDataNama.text?.clear()
            binding.etInputDataLokasi.text?.clear()
            binding.etInputDataStatus.text?.clear()
        }

        val adapter = AuditAdapter { auditToDelete ->
            auditViewModel.delete(auditToDelete)
        }
        binding.rvAudits.layoutManager = LinearLayoutManager(this)
        binding.rvAudits.adapter = adapter

        auditViewModel.allAudits.observe(this){ listAudit ->
            Log.d("AuditMainActivity", "Number of audits: ${listAudit.size}")
            if (listAudit.isEmpty()) {
                binding.tvStatus.text = getString(R.string.no_data)
                binding.tvStatus.visibility = android.view.View.VISIBLE
                binding.rvAudits.visibility = android.view.View.GONE
            } else {
                binding.tvStatus.visibility = android.view.View.GONE
                binding.rvAudits.visibility = android.view.View.VISIBLE
                adapter.submitList(listAudit)
            }
        }


    }
}
package com.learning.learningroomdatabase.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.learning.learningroomdatabase.R
import com.learning.learningroomdatabase.data.local.entity.AuditEntity

class AuditAdapter(
    private val onDeleteClicked: (AuditEntity) -> Unit
) : ListAdapter<AuditEntity, AuditAdapter.ViewHolder>(AuditDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_audit, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.deleteButton.setOnClickListener {
            onDeleteClicked(item)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvNama: TextView = itemView.findViewById(R.id.tv_item_nama)
        private val tvLokasi: TextView = itemView.findViewById(R.id.tv_item_lokasi)
        private val tvStatus: TextView = itemView.findViewById(R.id.tv_item_status)
        val deleteButton: Button = itemView.findViewById(R.id.btn_delete_item)

        fun bind(audit: AuditEntity) {
            tvNama.text = audit.nama_petugas
            tvLokasi.text = audit.lokasi_temuan
            // gunakan string resource untuk format agar dapat diterjemahkan
            tvStatus.text = itemView.context.getString(R.string.status_format, audit.status_prioritasi)
        }
    }

    class AuditDiffCallback : DiffUtil.ItemCallback<AuditEntity>() {
        override fun areItemsTheSame(oldItem: AuditEntity, newItem: AuditEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: AuditEntity, newItem: AuditEntity): Boolean {
            return oldItem == newItem
        }
    }
}

package com.example.kotlin_calculator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.history_item.view.*

class HistoryAdapter(private val historyItem: List<String>) :
    RecyclerView.Adapter<HistoryAdapter.HistoryItemViewHolder>() {


    class HistoryItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.tv_history
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryItemViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.history_item, parent, false)
        return HistoryItemViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return historyItem.size
    }

    override fun onBindViewHolder(holder: HistoryItemViewHolder, position: Int) {
        holder.textView.text = historyItem[position]
    }

}
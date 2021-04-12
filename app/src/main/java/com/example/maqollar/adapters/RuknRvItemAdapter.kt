package com.example.maqollar.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.maqollar.R
import com.example.maqollar.models.Maqol
import kotlinx.android.synthetic.main.maqol_rv_item.view.*


class RuknRvItemAdapter( var listener: OnRuknItemClickListener) :
    ListAdapter<Maqol, RuknRvItemAdapter.VH>(MaqolDiffUtil()) {

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(maqol: Maqol) {
            itemView.maqol_text.text = maqol.uzbek

            itemView.setOnClickListener {
                listener.onClick(maqol)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(parent.context).inflate(R.layout.maqol_rv_item, parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(getItem(position))
    }

    class MaqolDiffUtil : DiffUtil.ItemCallback<Maqol>() {
        override fun areItemsTheSame(oldItem: Maqol, newItem: Maqol): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Maqol, newItem: Maqol): Boolean {
            return oldItem.equals(newItem)
        }

    }

    interface OnRuknItemClickListener {
        fun onClick(maqol: Maqol)
    }
}
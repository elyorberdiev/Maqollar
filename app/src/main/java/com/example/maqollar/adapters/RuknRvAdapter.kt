package com.example.maqollar.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.maqollar.R
import com.example.maqollar.models.Rukn
import kotlinx.android.synthetic.main.rukn_rv_item.view.*

class RuknRvAdapter(var categoryList:List<Rukn>,var imageList:List<Int>,var listener:OnRuknItemClickListener) :RecyclerView.Adapter<RuknRvAdapter.VH>(){

    inner class VH(item: View):RecyclerView.ViewHolder(item){
        fun onBind(rukn: Rukn,image:Int) {

                itemView.category_count.text = rukn.count.toString()
                itemView.category_name.text = rukn.name
                itemView.rukn_image.setImageResource(image)

            itemView.setOnClickListener {
                listener.onClick(rukn,image)
            }
        }
    }

//    class MyDiffUtil:DiffUtil.ItemCallback<Rukn>(){
//        override fun areItemsTheSame(oldItem: Rukn, newItem: Rukn): Boolean {
//            return oldItem.id == newItem.id
//        }
//
//        override fun areContentsTheSame(oldItem: Rukn, newItem: Rukn): Boolean {
//            return oldItem.equals(newItem)
//        }
//
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(parent.context).inflate(R.layout.rukn_rv_item,parent,false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(categoryList[position],imageList[position])
    }

    override fun getItemCount(): Int = categoryList.size

    interface OnRuknItemClickListener{
        fun onClick(rukn: Rukn,position: Int)
    }

}
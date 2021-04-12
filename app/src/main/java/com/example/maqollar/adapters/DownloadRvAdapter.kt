package com.example.maqollar.adapters

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.maqollar.R
import kotlinx.android.synthetic.main.download_item.view.*
import kotlinx.android.synthetic.main.fragment_download.view.*

class DownloadRvAdapter(var imageList: List<Int>,var listener:OnDownloadItemClickListener):RecyclerView.Adapter<DownloadRvAdapter.VH>() {


    inner class VH(itemView:View):RecyclerView.ViewHolder(itemView){
        fun onBind(image: Int){

            itemView.item_img.setImageResource(image)

            itemView.setOnClickListener {
                listener.onClick(image)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(parent.context).inflate(R.layout.download_item,parent,false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(imageList[position])
    }

    override fun getItemCount(): Int = imageList.size

    interface OnDownloadItemClickListener{
        fun onClick(image: Int)
    }
}
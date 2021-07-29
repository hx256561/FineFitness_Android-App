package com.example.streamchatdemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.streamchatdemo.databinding.TrainMenuRowItemBinding
import com.example.streamchatdemo.databinding.UserRowLayoutBinding
import com.example.streamchatdemo.model.trainMenuItem
import com.google.protobuf.Empty

class trainMenuAdapter:RecyclerView.Adapter<trainMenuAdapter.MyViewHolder>() {

    var trainMenuList= mutableListOf<trainMenuItem>()
    class MyViewHolder(val binding: TrainMenuRowItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): trainMenuAdapter.MyViewHolder {
        return trainMenuAdapter.MyViewHolder(
            TrainMenuRowItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: trainMenuAdapter.MyViewHolder, position: Int) {
        holder.binding.trainMenuItem.setText(trainMenuList[position].itemName)
    }

    override fun getItemCount(): Int {
        return trainMenuList.size
    }

    fun addTrainMenuItem(item:trainMenuItem){
        trainMenuList.add(item)
    }


}
package com.example.streamchatdemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.streamchatdemo.databinding.TrainMenuRowItemBinding
import com.example.streamchatdemo.model.trainMenuItem
import com.example.streamchatdemo.studentSystem.dataBase
import com.google.firebase.firestore.FirebaseFirestore

class trainMenuAdapter:RecyclerView.Adapter<trainMenuAdapter.MyViewHolder>() {

    var trainMenuList= mutableListOf<trainMenuItem>()
    class MyViewHolder(val binding: TrainMenuRowItemBinding) : RecyclerView.ViewHolder(binding.root)
    private val db = FirebaseFirestore.getInstance()

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
        holder.binding.trainMenuItem.setOnClickListener {
            if(holder.binding.trainMenuItem.isChecked.equals(false)){
                holder.binding.trainMenuItem.isChecked=true
                //-------------------------------------------------
                var result:String= String()
                //var mid:Number
                db.collection("Userlist").document("0bwQiauYGM8699qnhEZ9")
                    .get()
                    .addOnCompleteListener {
                        if(it.isSuccessful){
                            result=it.result!!.data!!.getValue("exp").toString()
                        }
                        db.collection("Userlist").document("0bwQiauYGM8699qnhEZ9").update("exp",result.toInt()+10)
                    }
                //db.collection("Userlist").document("0bwQiauYGM8699qnhEZ9").update("exp",mid.toInt()+10)
                //-------------------------------------------------
            }else{
                holder.binding.trainMenuItem.isChecked=false
            }
        }

    }

    override fun getItemCount(): Int {
        return trainMenuList.size
    }

    fun addTrainMenuItem(item:trainMenuItem){
        trainMenuList.add(item)
    }


}
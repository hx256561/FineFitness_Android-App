package com.example.streamchatdemo.studentSystem

import android.app.AlertDialog
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.streamchatdemo.R
import com.example.streamchatdemo.adapter.matchAdapter
import com.example.streamchatdemo.adapter.trainMenuAdapter
import com.example.streamchatdemo.databinding.StudentTrainMenuFragmentBinding
import com.example.streamchatdemo.model.trainMenuItem

class studentTrainMenu_fragment: Fragment() {

    private var _binding:StudentTrainMenuFragmentBinding?=null
    private val binding get()=_binding!!
    private val trainMenuAdapter by lazy { trainMenuAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
            _binding= StudentTrainMenuFragmentBinding.inflate(inflater,container,false)

            var item1=trainMenuItem("Push up * 100")
            var item2=trainMenuItem("Push up * 50")
            trainMenuAdapter.addTrainMenuItem(item1)
            trainMenuAdapter.addTrainMenuItem(item2)

        binding.addTrainMenuItem.setOnClickListener {
            addTrainItemDialog()
        }

            setUpRecyclerView()

        return binding.root
    }

    private fun setUpRecyclerView(){
        binding.trainMenu.layoutManager=LinearLayoutManager(requireContext())
        binding.trainMenu.adapter=trainMenuAdapter
    }

    private fun addTrainItemDialog(){
        var builder=AlertDialog.Builder(this.context)
        var v=layoutInflater.inflate(R.layout.add_menu_item_layout,null)
        builder.setView(v)
        var input=v.findViewById<EditText>(R.id.add_item_input)
        var finishBtn=v.findViewById<Button>(R.id.add_finish)
        var cancelBtn=v.findViewById<Button>(R.id.add_cancel)
        var dialog=builder.create()
        dialog.show()

        finishBtn.setOnClickListener {
            //之後這裡要寫把菜單加到資料庫的code
            val t=Toast.makeText(this.context,"Item added",Toast.LENGTH_SHORT)
            t.setGravity(Gravity.CENTER_VERTICAL,0,0)
            t.show()
            dialog.dismiss()
        }
        cancelBtn.setOnClickListener {
            val t=Toast.makeText(context,"Canceled",Toast.LENGTH_SHORT)
            t.setGravity(Gravity.CENTER_VERTICAL,0,0)
            t.show()
            dialog.dismiss()
        }
    }

}
package com.example.streamchatdemo.studentSystem

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.streamchatdemo.R
import com.example.streamchatdemo.databinding.StudentSubscribe2FragmentBinding
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

// 展開清單(expandableList)參考: https://medium.com/chikuwa-tech-study/android-雙層清單expandablelistview-987f03869296
class studentSubscribe2_fragment: Fragment() {
    private var _binding:StudentSubscribe2FragmentBinding?=null
    private val binding get() = _binding!!

    private val db = FirebaseFirestore.getInstance()

    private var coachNames= mutableListOf<String>()
    private var coachSkills= mutableListOf<List<String>>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (_binding==null){
            _binding= StudentSubscribe2FragmentBinding.inflate(inflater,container,false)

            val listView=binding.listView
            val coaches= listOf("coach Lin","coach Woods","coach Brook")
            val intros= listOf(
                listOf("Skills:\nboxing, wrestling","Available time:\n(calender view)"),
                listOf("Skills:","Available time:\n(calender view)"),
                listOf("Skills:","Available time:\n(calender view)")
            )
    //-------------教練與資料庫連動---------------------------------------------------------------------------------
            db.collection("Userlist").whereEqualTo("isCoach",1).get()
                .addOnSuccessListener {
                        documents ->
                    for(document in documents){
                        coachNames.add(document.data.getValue("firstname").toString())
                        val skillArray= listOf(document.data.getValue("skills").toString(),"Press to\n" +
                                "Make an appointment")
                        //coachSkills.add(document.data.getValue("skills").toString())
                        coachSkills.add(skillArray)
                    }
                    val adapter=ExpandableListViewAdapter(requireContext(),coachNames,coachSkills)
                    listView.setAdapter(adapter)
            }

    //----------------------------------------------------------------------------------------------------------

            var cal = Calendar.getInstance()
            val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                cal.set(Calendar.YEAR, year) // 取得現在的日期
                cal.set(Calendar.MONTH, month)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            }
            val timeSetListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                cal.set(Calendar.HOUR, hourOfDay) // 取得現在的時間
                cal.set(Calendar.MINUTE, minute)
            }
            val builder = AlertDialog.Builder(context)
            val positiveListener = DialogInterface.OnClickListener { dialog, which ->
                Toast.makeText(context, "Appointment sent", Toast.LENGTH_SHORT).show()
            }

            listView.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->
                builder.setMessage("Send request?").setPositiveButton("SURE", positiveListener).setNeutralButton("WAIT", null).show()
                TimePickerDialog(requireContext(), timeSetListener, cal.get(Calendar.HOUR), Calendar.MINUTE, false).show()
                DatePickerDialog(
                    requireContext(),
                    dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()

                //Toast.makeText(context, "hi", Toast.LENGTH_SHORT).show()

                false
            }
        }
        return binding.root
    }

    class ExpandableListViewAdapter(
        private var context: Context,
        private var coachName: List<String>, //紀錄推薦教練的list
        private var coachIntro: List<List<String>>, //紀錄(理想是)時間表和專長；展開後是2Dlist,之後視情況調整
    ): BaseExpandableListAdapter(){
        // 取得群組物件和子項目物件
        override fun getGroup(groupPosition: Int): Any {
            return coachName[groupPosition]
        }
        override fun getChild(groupPosition: Int, childPosition: Int): Any {
            return coachIntro[groupPosition][childPosition]
        }

        // 取得群組物件和子項目物件數量
        override fun getGroupCount(): Int {
            return coachName.size
        }


        override fun getChildrenCount(groupPosition: Int): Int {
            return coachIntro[groupPosition].size
        }



        // 項目id相關設定
        override fun getGroupId(groupPosition: Int): Long {
            return groupPosition.toLong()
        }
        override fun getChildId(groupPosition: Int, childPosition: Int): Long {
            return (groupPosition * 100 + childPosition).toLong() //可依照需求和數量自行調整
        }
        override fun hasStableIds(): Boolean {
            return true
        }

        // 定義子項目是否可以被點擊
        override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
            if(childPosition==1){
                return true //idx 1 places button that leads to date picker
            }
            return false
            //return true
        }

        // 串接資料與項目畫面
        override fun getGroupView(
            groupPosition: Int,
            isExpanded: Boolean,
            convertView: View?,
            parent: ViewGroup?
        ): View {
            val view=convertView ?:LayoutInflater.from(context).inflate(R.layout.subscribe2_item_coach, null)
            val textView=view.findViewById<TextView>(R.id.txtCoachName)
            textView.text = coachName[groupPosition]

            return view
        }
        override fun getChildView(
            groupPosition: Int,
            childPosition: Int,
            isLastChild: Boolean,
            convertView: View?,
            parent: ViewGroup?
        ): View {
            val view=convertView ?:LayoutInflater.from(context).inflate(R.layout.subscribe2_item_intro, null)
            val textView=view.findViewById<TextView>(R.id.txtCoachIntro)
            textView.text = coachIntro[groupPosition][childPosition]
            return view
        }

    }
}
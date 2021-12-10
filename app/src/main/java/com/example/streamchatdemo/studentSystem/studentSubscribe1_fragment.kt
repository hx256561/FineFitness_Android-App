package com.example.streamchatdemo.studentSystem

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.streamchatdemo.R
import com.example.streamchatdemo.databinding.StudentSubscribe1FragmentBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class studentSubscribe1_fragment: Fragment() {
    private var _binding:StudentSubscribe1FragmentBinding?=null
    private val binding get() = _binding!!

    private var coachNames= mutableListOf<String>()
    private var coachSkills= mutableListOf<List<String>>()

    private val db = FirebaseFirestore.getInstance()

    private val authUser= FirebaseAuth.getInstance()
    private val currentAuthUser=authUser.currentUser

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= StudentSubscribe1FragmentBinding.inflate(inflater,container,false)
        val listView=binding.listView
        //-------

        //-------
        //-------
        db.collection("Userlist").whereEqualTo("isCoach",1).get()
            .addOnSuccessListener {
                    documents ->
                for(document in documents){
                    coachNames.add(document.data.getValue("firstname").toString())
                    val skillArray= listOf(document.data.getValue("skills").toString(),"Appointment time:\n" +
                            "(calender view)")
                    coachSkills.add(skillArray)
                }
                /*
                val adapter= studentSubscribe2_fragment.ExpandableListViewAdapter(
                    requireContext(),
                    coachNames,
                    coachSkills
                )*/
                val adapter = ExpandableListViewAdapter1(
                    requireContext(),
                    coachNames,
                    coachSkills
                )
                listView.setAdapter(adapter)
            }
        //-------
        listView.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->
            false
        }

        return binding.root
    }

    class ExpandableListViewAdapter1(
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
            return true
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
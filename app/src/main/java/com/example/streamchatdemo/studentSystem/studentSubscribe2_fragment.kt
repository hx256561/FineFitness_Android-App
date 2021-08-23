package com.example.streamchatdemo.studentSystem

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.streamchatdemo.R
import com.example.streamchatdemo.databinding.StudentSubscribe2FragmentBinding
import com.example.streamchatdemo.databinding.Subscribe2ItemIntroBinding
// 展開清單(expandableList)參考: https://medium.com/chikuwa-tech-study/android-雙層清單expandablelistview-987f03869296
class studentSubscribe2_fragment: Fragment() {
    private var _binding:StudentSubscribe2FragmentBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (_binding==null){
            _binding= StudentSubscribe2FragmentBinding.inflate(inflater,container,false)

            val listView=binding.listView
            val coaches= listOf("林教練","coach2","coach3")
            val intros= listOf(
                listOf("專長","可約時間"),
                listOf("專長","可約時間"),
                listOf("專長","可約時間")
            )
            val adapter=ExpandableListViewAdapter(requireContext(),coaches,intros)
            listView.setAdapter(adapter)
        }
        return binding.root
    }

    class ExpandableListViewAdapter(
        private val context: Context,
        private val coachName: List<String>, //紀錄推薦教練的list
        private val coachIntro: List<List<String>> //紀錄(理想是)時間表和專長；展開後是2Dlist,之後視情況調整
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
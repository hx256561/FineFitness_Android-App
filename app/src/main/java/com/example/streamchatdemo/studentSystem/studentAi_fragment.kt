package com.example.streamchatdemo.studentSystem

import android.app.Activity.RESULT_OK
import android.content.ContentValues.TAG
import android.content.Intent
import android.icu.text.DateTimePatternGenerator.PatternInfo.OK
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.streamchatdemo.databinding.StudentAiFragmentBinding
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask


class studentAi_fragment: Fragment() {

    private var _binding:StudentAiFragmentBinding?=null
    private val binding get()=_binding!!

    var VIDEO: Int=0
    //var uri:Uri?=null
    lateinit var uri:Uri
    lateinit var mStorage:StorageReference

    private val authUser= FirebaseAuth.getInstance()
    private val currentAuthUser=authUser.currentUser
    private var authUid=currentAuthUser?.uid

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= StudentAiFragmentBinding.inflate(inflater,container,false)

        mStorage=FirebaseStorage.getInstance().getReference("Uploads")
        Log.d(TAG, "here yyyyy onCreateView: ")

        binding.uploadVideo.setOnClickListener(View.OnClickListener{
            view:View? -> val intent= Intent()
            intent.setType("video/*")
            intent.setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(Intent.createChooser(intent,"Select Video"),VIDEO)
        })

        binding.video.setVideoPath("https://firebasestorage.googleapis.com/v0/b/chat1-6a6ae.appspot.com/o/Uploads%2Fvideo%3A117?alt=media&token=0b431d24-5374-426d-9b08-3adeede8228c")
        binding.video.start()


        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //var uriText = binding.url
        if(resultCode==RESULT_OK){
            if(requestCode==VIDEO){
                uri= data!!.data!!
                //uriText.text=uri.toString()
                upload()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun upload(){
        //start here
        var mReference=mStorage.child(uri!!.lastPathSegment!!)
        try{
            mReference.putFile(uri).addOnSuccessListener {
                val result=it.metadata!!.reference!!.downloadUrl
                result.addOnSuccessListener {
                    val link=it.toString()
                    //link是影片網址
                    Log.d(TAG, "xxxxxxxx upload: "+link)
                }

                Toast.makeText(context,"Successfully Uploaded"+authUid,Toast.LENGTH_LONG).show()
                //上面表示已經傳好
                //id 是 authUid

            }
        }catch (e:Exception){
            Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show()
        }
    }


}
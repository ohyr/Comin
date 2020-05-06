package com.example.comin.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.comin.Fragment.MarketInfo.MarketInfoActivity
import com.example.comin.R
import com.example.comin.Utils.FirebaseUtils
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.model.mutation.Precondition.exists
import kotlinx.android.synthetic.main.fragment_first.view.*
import java.nio.file.Files.exists

/**
 * A simple [Fragment] subclass.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view : View = inflater.inflate(R.layout.fragment_first, container, false)

        val list_array = arrayListOf<ContentsListModel>(

            ContentsListModel(R.drawable.list1,"Lang1",1,""),
            ContentsListModel(R.drawable.list2,"Lang2",1,"d"),
            ContentsListModel(R.drawable.list3,"Lang3",1,"d"),
            ContentsListModel(R.drawable.list4,"Lang4",1,"d"),
            ContentsListModel(R.drawable.list5,"Lang5",1,"d"),
            ContentsListModel(R.drawable.list6,"Lang6",1,"d"),
            ContentsListModel(R.drawable.list7,"Lang7",1,"d"),
            ContentsListModel(R.drawable.list8,"Lang8",1,"d"),
            ContentsListModel(R.drawable.list9,"Lang9",1,"d")

            )

        val list_adapter = FirstFragAdapter(requireContext(), list_array)
        view.listview_first_fragment.adapter = list_adapter

        FirebaseUtils.db
            .collection("zzim")
            .document(FirebaseUtils.getUid())
            .get()
            .addOnSuccessListener { documentSnapshot ->
               if(documentSnapshot.exists() == true){
                   //Data 필드가 있을 때
               } else {
                   //Data 필드가 없을 때
                   val lecture = hashMapOf(
                       "Lang1" to "",
                       "Lang2" to "",
                       "Lang3" to "",
                       "Lang4" to "",
                       "Lang5" to "",
                       "Lang6" to "",
                       "Lang7" to "",
                       "Lang8" to "",
                       "Lang9" to ""
                       )

                   FirebaseUtils.db
                       .collection("zzim")
                       .document(FirebaseUtils.getUid())
                       .set(lecture)
                       .addOnSuccessListener {  }
                       .addOnFailureListener {  }
               }
            }
            .addOnFailureListener {

            }

        view.listview_first_fragment.setOnItemClickListener { adapterView, view, i, l ->

            val intent = Intent(requireContext(), MarketInfoActivity::class.java)
            intent.putExtra("title", list_array.get(i).title)
            startActivity(intent)

        }

        return view
    }

}

package com.example.comin.Fragment.MarketInfo

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.widget.Toast
import com.example.comin.R
import com.example.comin.Utils.FirebaseUtils
import kotlinx.android.synthetic.main.activity_market_info.*

class MarketInfoActivity : AppCompatActivity() {

    private val TAG = MarketInfoActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_market_info)

        Log.e(TAG, "onCreate")

        // FirebaseUtils 사용
        //val uid = FirebaseUtils.getUid()
        //val db = FirebaseUtils.db

        //찜 title 넘기기
        lecture_text.text = intent.getStringExtra("title")

        //찜 여부 확인
        FirebaseUtils.db
            .collection("zzim")
            .document(FirebaseUtils.getUid())
            .get()
            .addOnSuccessListener { documentSnapshot ->
                Log.e(TAG, documentSnapshot.get(intent.getStringExtra("title")).toString())

                if(documentSnapshot.get(intent.getStringExtra("title")) == true){
                    header_zzim.text = "하트뿅뿅 찜 되었습니다."
                    header_zzim.setTextColor(Color.BLUE)
                }
            }
            .addOnFailureListener{}

        //찜기능
        zzim.setOnClickListener{

            if(header_zzim.text.equals("하트뿅뿅 찜 되었습니다.")) {
                header_zzim.text = "하트뿅뿅 찜"
                header_zzim.setTextColor(Color.RED)

                FirebaseUtils.db
                    .collection("zzim")
                    .document(FirebaseUtils.getUid())
                    .update(intent.getStringExtra("title"), "")
                    .addOnSuccessListener {
                        Toast.makeText(this, "성공", Toast.LENGTH_LONG).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "실패", Toast.LENGTH_LONG).show()
                    }
            }else {
                header_zzim.text = "하트뿅뿅 찜 되었습니다"
                header_zzim.setTextColor(Color.BLUE)

                FirebaseUtils.db
                    .collection("zzim")
                    .document(FirebaseUtils.getUid())
                    .update(intent.getStringExtra("title"), true)
                    .addOnSuccessListener {
                        Toast.makeText(this, "성공", Toast.LENGTH_LONG).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "실패", Toast.LENGTH_LONG).show()
                    }
            }
        }

        //처음에 콘텐츠 메뉴 띄워주기
        //해당 메뉴 글씨 크기 바꿔주고
        figure_1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25F)
        figure_2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15F)
        figure_3.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15F)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_area, ContentFragment())
            .commit()

        figure_1.setOnClickListener{

            //해당 메뉴 글씨 크기 바꿔주고
            figure_1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25F)
            figure_2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15F)
            figure_3.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15F)

            //프레그먼트 띄우기
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_area, ContentFragment())
                .commit()

        }
        figure_2.setOnClickListener{
            figure_1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15F)
            figure_2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25F)
            figure_3.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15F)

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_area, InfoFragment())
                .commit()
        }
        figure_3.setOnClickListener{
            figure_1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15F)
            figure_2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15F)
            figure_3.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25F)

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_area, ReviewFragment())
                .commit()
        }


    }
}

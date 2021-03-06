package com.change.demox.views.recyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.change.demox.R
import com.change.demox.views.recyclerview.ceiling.RecyclerviewCeilingActivity
import com.change.demox.views.recyclerview.ceilingTwo.RecyclerviewCeilingTwoActivity
import com.change.demox.views.recyclerview.muti.RecyclerviewCategoryActivity
import kotlinx.android.synthetic.main.activity_recycler.*

class RecyclerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)
        initView()
    }

   private fun initView(){
        btn_category.setOnClickListener {
            val intent = Intent(this, RecyclerviewCategoryActivity::class.java)
            startActivity(intent)
        }
       btn_ceiling.setOnClickListener{
           val intent2 = Intent(this, RecyclerviewCeilingActivity::class.java)
           startActivity(intent2)
       }

       btn_sticky.setOnClickListener {
           val intent3 = Intent(this, RecyclerviewCeilingTwoActivity::class.java)
           startActivity(intent3)
       }
    }
}
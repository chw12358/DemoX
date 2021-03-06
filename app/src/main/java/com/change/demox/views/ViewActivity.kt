package com.change.demox.views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.change.demox.R
import com.change.demox.views.edittext.EditTextActivity
import com.change.demox.views.recyclerview.RecyclerActivity
import com.change.demox.views.spinner.SpinnerActivity
import com.change.demox.views.textview.TextViewActivity
import kotlinx.android.synthetic.main.activity_view.*


class ViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)
        initView()
    }

    private fun initView() {
        btn_spinner.setOnClickListener {
            val intent = Intent(this, SpinnerActivity::class.java)
            startActivity(intent)
        }
        btn_recyclerview.setOnClickListener {
            val intent = Intent(this, RecyclerActivity::class.java)
            startActivity(intent)
        }
        btn_textview.setOnClickListener {
            val intent = Intent(this, TextViewActivity::class.java)
            startActivity(intent)
        }
        btn_edittext.setOnClickListener {
            val intent = Intent(this, EditTextActivity::class.java)
            startActivity(intent)
        }
    }
}
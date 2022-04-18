package com.designmaster.sukar.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import com.designmaster.sukar.R

class TermsActivity : AppCompatActivity(), View.OnClickListener {


    lateinit var termstv:TextView
    var btnBack: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.terms_conditions)
        initUI()
    }

    private fun initUI() {
        btnBack = findViewById(R.id.btnback)
        btnBack!!.setOnClickListener(this)

    }


    override fun onBackPressed() {
        // super.onBackPressed()
        val mainIntent = Intent(this, SignupLoginActivity::class.java)
        startActivity(mainIntent)
        finish()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnback -> {
                val mainIntent = Intent(this, SignupLoginActivity::class.java)
                startActivity(mainIntent)
                finish()
            }
        }

    }




}
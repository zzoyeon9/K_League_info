package com.example.k_league_info

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_teamdetail.*

class teamdetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_teamdetail)

        tmp_buttton.setOnClickListener{
            val nextIntent = Intent(this, Player::class.java)
            startActivity(nextIntent)
        }
    }
}
package com.example.k_league_info

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_teamdetail.*
import android.content.Intent

class TeamdetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teamdetail)

        tmp_buttton.setOnClickListener {
            val intent = Intent(this, Player::class.java)
            startActivity(intent)
        }
    }
}
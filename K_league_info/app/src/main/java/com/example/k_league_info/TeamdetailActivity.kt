package com.example.k_league_info

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class TeamdetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState : Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teamdetail)

        val button = findViewById<Button>(R.id.tmp_buttton);
        button.setOnClickListener{
            val nextIntent = Intent(this, PlayerActivity::class.java)
            startActivity(nextIntent)
        }

    }
}


package com.example.k_league_info.ui.team

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.ImageButton
import android.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.k_league_info.R
import com.example.k_league_info.R.layout.fragment_team
import com.example.k_league_info.TeamdetailActivity
import kotlinx.android.synthetic.main.fragment_team.*

class TeamFragment : Fragment() {

    /**
     * @author : 구본승
     * @return : root: View => fragment_team
     * @description : View가 만들어졌을때, fragment_team에서 imagebutton을 받아와 해당 버튼을 누르면 해당 버튼의 데이터를 넘겨받은 teamdetailActivity를 열어준다.
     * */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root: View = inflater.inflate(fragment_team, container, false)

        // 1번 row
        val ulsanImageButton = root.findViewById<ImageButton>(R.id.ulsanImagebutton)
        ulsanImageButton.setOnClickListener {
            activity?.let {
                val nextIntent = Intent(context, TeamdetailActivity::class.java)
                nextIntent.putExtra("teamName","ulsan")
                startActivity(nextIntent)
            }
        }

        val jeonbukImageButton = root.findViewById<ImageButton>(R.id.jeonbukImagebutton)
        jeonbukImageButton.setOnClickListener {
            activity?.let {
                val nextIntent = Intent(context, TeamdetailActivity::class.java)
                nextIntent.putExtra("teamName","jeonbuk")
                startActivity(nextIntent)
            }
        }

        // 2번 row
        val sangjuImageButton = root.findViewById<ImageButton>(R.id.sangjuImagebutton)
        sangjuImageButton.setOnClickListener {
            activity?.let {
                val nextIntent = Intent(context, TeamdetailActivity::class.java)
                nextIntent.putExtra("teamName","sangju")
                startActivity(nextIntent)
            }
        }

        val pohangImageButton = root.findViewById<ImageButton>(R.id.pohangImagebutton)
        pohangImageButton.setOnClickListener {
            activity?.let {
                val nextIntent = Intent(context, TeamdetailActivity::class.java)
                nextIntent.putExtra("teamName","pohang")
                startActivity(nextIntent)
            }
        }

        // 3번 row
        val daeguImageButton = root.findViewById<ImageButton>(R.id.daeguImagebutton)
        daeguImageButton.setOnClickListener {
            activity?.let {
                val nextIntent = Intent(context, TeamdetailActivity::class.java)
                nextIntent.putExtra("teamName","daegu")
                startActivity(nextIntent)
            }
        }

        val seoulImageButton = root.findViewById<ImageButton>(R.id.seoulImagebutton)
        seoulImageButton.setOnClickListener {
            activity?.let {
                val nextIntent = Intent(context, TeamdetailActivity::class.java)
                nextIntent.putExtra("teamName","seoul")
                startActivity(nextIntent)
            }
        }

        // 4번 row
        val seongnamImageButton = root.findViewById<ImageButton>(R.id.seongnamImagebutton)
        seongnamImageButton.setOnClickListener {
            activity?.let {
                val nextIntent = Intent(context, TeamdetailActivity::class.java)
                nextIntent.putExtra("teamName","seongnam")
                startActivity(nextIntent)
            }
        }

        val gangwonImageButton = root.findViewById<ImageButton>(R.id.gangwonImagebutton)
        gangwonImageButton.setOnClickListener {
            activity?.let {
                val nextIntent = Intent(context, TeamdetailActivity::class.java)
                nextIntent.putExtra("teamName","gangwon")
                startActivity(nextIntent)
            }
        }

        // 5번 row
        val busanImageButton = root.findViewById<ImageButton>(R.id.busanImagebutton)
        busanImageButton.setOnClickListener {
            activity?.let {
                val nextIntent = Intent(context, TeamdetailActivity::class.java)
                nextIntent.putExtra("teamName","busan")
                startActivity(nextIntent)
            }
        }

        val gwangjuImageButton = root.findViewById<ImageButton>(R.id.gwangjuImagebutton)
        gwangjuImageButton.setOnClickListener {
            activity?.let {
                val nextIntent = Intent(context, TeamdetailActivity::class.java)
                nextIntent.putExtra("teamName","gwangju")
                startActivity(nextIntent)
            }
        }

        // 6번 row
        val suwonImageButton = root.findViewById<ImageButton>(R.id.suwonImagebutton)
        suwonImageButton.setOnClickListener {
            activity?.let {
                val nextIntent = Intent(context, TeamdetailActivity::class.java)
                nextIntent.putExtra("teamName","suwon")
                startActivity(nextIntent)
            }
        }

        val incheonImagebutton = root.findViewById<ImageButton>(R.id.incheonImagebutton)
        incheonImagebutton.setOnClickListener {
            activity?.let {
                val nextIntent = Intent(context, TeamdetailActivity::class.java)
                nextIntent.putExtra("teamName","incheon")
                startActivity(nextIntent)
            }
        }


        return root;
    }

    /**
     * @author : 구본승
     * @description : 필수 override 함수라서 넣음
     * */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
package com.example.k_league_info.ui.team

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.example.k_league_info.R
import com.example.k_league_info.R.layout.fragment_team
import com.example.k_league_info.ScoredetailActivity
import com.example.k_league_info.TeamdetailActivity

class TeamFragment : Fragment() {

    /**
     * @author : 구본승
     * @throws : NULL
     * @return : root: View => fragment_team
     * @description : View가 만들어졌을때, fragment_team에서 imagebutton을 받아와 해당 버튼을 누르면 teamdetailActivity를 열어준다.
    * */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root: View = inflater.inflate(fragment_team, container, false)

        val gangwonImageButton = root.findViewById<ImageButton>(R.id.gangwonImagebutton)
        gangwonImageButton.setOnClickListener {
            activity?.let {
                val nextIntent = Intent(context, TeamdetailActivity::class.java)
                startActivity(nextIntent)
            }
        }

        val incheonImagebutton = root.findViewById<ImageButton>(R.id.incheonImagebutton)
        incheonImagebutton.setOnClickListener {
            activity?.let {
                val nextIntent = Intent(context, TeamdetailActivity::class.java)
                startActivity(nextIntent)
            }
        }


        return root;
    }

    /**
     * @author : 구본승
     * @throws : NULL
     * @return : NULL
     * @description : 필수 override 함수라서 넣음
     * */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
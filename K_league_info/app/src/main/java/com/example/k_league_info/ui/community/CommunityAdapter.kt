package com.example.k_league_info.ui.community

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.k_league_info.R

/*
    리사이클러뷰 어댑터
*/

class CommunityAdapter(val context: Context, val boardList: ArrayList<CommunityBoard>) :
    RecyclerView.Adapter<CommunityAdapter.Holder>() {
    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.title)
        val recommand = itemView.findViewById<TextView>(R.id.recommand)
        val content = itemView.findViewById<TextView>(R.id.content)
        val comment = itemView.findViewById<TextView>(R.id.comment)

        fun bind(communityBoard: CommunityBoard, context: Context) {
            title.text = communityBoard.title
            recommand.text = communityBoard.recommend
            content.text = communityBoard.content
            comment.text = communityBoard.comment

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, PostScreen::class.java)
                intent.putExtra("num", communityBoard.number)
                intent.putExtra("title", communityBoard.title)
                intent.putExtra("content", communityBoard.content)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_communityboard, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return boardList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(boardList[position], context)

    }
}
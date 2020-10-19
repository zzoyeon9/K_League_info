package com.example.k_league_info.ui.community

import android.animation.ValueAnimator
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.k_league_info.R

class CommunityAdapter(val context: Context, private val boardList: ArrayList<CommunityBoard>) :
    RecyclerView.Adapter<CommunityAdapter.Holder>() {
    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val content: TextView = itemView.findViewById(R.id.content)
        fun bind(communityBoard: CommunityBoard, context: Context) {
            title.text = communityBoard.title
            content.text = communityBoard.content
            content.visibility = View.GONE

            itemView.setOnClickListener {
                val height = content.resources.displayMetrics.density * 150
                if(content.visibility == View.GONE) {
                    val va = ValueAnimator.ofInt(0, height.toInt()).apply {
                        duration = 500
                        addUpdateListener{
                            val params = content.layoutParams
                            params.height = animatedValue as Int
                            content.layoutParams = params
                            content.visibility = View.VISIBLE
                        }
                    }
                    va.start()
                }
                else {
                    val va = ValueAnimator.ofInt(height.toInt(), 0).apply {
                        duration = 500
                        addUpdateListener{
                            val params = content.layoutParams
                            params.height = animatedValue as Int
                            content.layoutParams = params
                            content.visibility = View.GONE
                        }
                    }
                    va.start()
                }
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
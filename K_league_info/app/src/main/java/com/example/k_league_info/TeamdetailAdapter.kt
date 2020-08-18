package com.example.k_league_info

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView


class TeamdetailAdapter(private val items: ArrayList<TeamdetailBoard>) :
    RecyclerView.Adapter<TeamdetailAdapter.Holder>() {

    inner class Holder(v: View) : RecyclerView.ViewHolder(v) {
        val playerThumbnail = v.findViewById<ImageView>(R.id.player_thumbnail)
        val playerName = v.findViewById<TextView>(R.id.player_name)

        fun bind(board: TeamdetailBoard, context: Context) {
            playerThumbnail.setImageDrawable(board.image)
            playerName.text = board.name

            itemView.setOnClickListener {
                val nextIntent = Intent(itemView.context, PlayerActivity::class.java)
                context.startActivity(nextIntent)
            }
        }
    }


    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = items[position]
        val listener = View.OnClickListener { it ->
            Toast.makeText(it.context, "Clicked: ${item.name}", Toast.LENGTH_SHORT).show()
        }
        holder.apply {
            // bind(listener, item)
            itemView.tag = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflatedView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_teamdetail, parent, false)
        return Holder(inflatedView)
    }


}
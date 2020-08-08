package com.example.k_league_info

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_teamdetail.view.*


class TeamdetailAdapter(private val items: ArrayList<TeamdetailBoard>) :
    RecyclerView.Adapter<TeamdetailAdapter.ViewHolder>() {

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: TeamdetailAdapter.ViewHolder, position: Int) {
        val item = items[position]
        val listener = View.OnClickListener { it ->
            Toast.makeText(it.context, "Clicked: ${item.name}", Toast.LENGTH_SHORT).show()
        }
        holder.apply {
            bind(listener, item)
            itemView.tag = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            TeamdetailAdapter.ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_teamdetail, parent, false)
        return TeamdetailAdapter.ViewHolder(inflatedView)
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        private var view: View = v

        fun bind(listener: View.OnClickListener, item: TeamdetailBoard) {
            view.thumbnail.setImageDrawable(item.image)
            view.name.text = item.name
            view.setOnClickListener(listener)
        }
    }
}
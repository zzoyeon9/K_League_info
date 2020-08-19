package com.example.k_league_info

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import androidx.annotation.LayoutRes
import kotlinx.android.synthetic.main.item_teamdetail.view.*

// adapter is used to set data to Recycler View from Data Source
class TeamdetailAdapter(private val data: ArrayList<TeamdetailBoard>) :
    RecyclerView.Adapter<TeamdetailAdapter.Holder>() {

    inner class Holder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener{
        private var view: View = v
        private val thumbnail = view.player_thumbnail
        private val name = view.player_name

        init{
            v.setOnClickListener(this)
        }

        fun bind(board: TeamdetailBoard, context: Context) {
            val uri = Uri.parse(board.imageurl)
            thumbnail.setImageURI(uri)
            name.text = board.name
        }

        override fun onClick(v: View?) {
            Log.d("Recyerview","clicked")
            val nextIntent = Intent(itemView.context, PlayerActivity::class.java)
            itemView.context.startActivity(nextIntent)
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TeamdetailAdapter.Holder {
        fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
            return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
        }

        val inflatedView = parent.inflate(R.layout.item_teamdetail, false)
        return Holder(inflatedView)

    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = data[position]
        holder.apply {
            // bind(listener, item)
            itemView.tag = item
        }
    }

}
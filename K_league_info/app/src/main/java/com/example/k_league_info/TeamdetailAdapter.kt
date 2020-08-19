package com.example.k_league_info

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import androidx.annotation.LayoutRes
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_teamdetail.view.*

// adapter is used to set data to Recycler View from Data Source
class TeamdetailAdapter(val context: Context, private val data: ArrayList<TeamdetailBoard>) :
    RecyclerView.Adapter<TeamdetailAdapter.Holder>() {

    inner class Holder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener{
        init{
            v.setOnClickListener(this)
        }

        fun bind(board: TeamdetailBoard, context: Context) {
            val uri = Uri.parse(board.imageurl)
            Glide.with(itemView.context).load(uri).into(itemView.player_thumbnail)
            itemView.player_name.text = board.name
            Log.d("itemView Name",uri.toString())
        }

        // 여기서 PlayerActivity로 넘길 data 명시하면 됨
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

    override fun onBindViewHolder(holder: TeamdetailAdapter.Holder, position: Int) {
        holder.bind(data[position], context)
    }
}
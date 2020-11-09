package com.example.k_league_info

import android.content.Context
import android.content.Intent
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

    inner class Holder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        init {
            v.setOnClickListener(this)
        }

        fun bind(board: TeamdetailBoard, context: Context) {
            val img_tmp = board.img_src.toString().replace("https:","http:")
            Glide.with(itemView.context).asBitmap().load(img_tmp).override(120, 120).fitCenter()
                .placeholder(R.drawable.loading)
                .into(itemView.player_thumbnail)
            itemView.player_name.text = board.name
            Log.d("log : img_url ", board.img_src.toString())
            Log.d("log : board name : ", board.name)
        }

        // 여기서 PlayerActivity로 넘길 data 명시하면 됨
        override fun onClick(v: View?) {
            Log.d("log : Recylcerview", "clicked")
            val nextIntent = Intent(itemView.context, PlayerActivity::class.java)
            Log.d("log : View! ", data.get(position).toString())
            nextIntent.putExtra("belong", data.get(position).belong)
            nextIntent.putExtra("name", data.get(position).name)
            nextIntent.putExtra("birthdt", data.get(position).birthdt)
            nextIntent.putExtra("height", data.get(position).height)
            nextIntent.putExtra("weight", data.get(position).weight)
            nextIntent.putExtra("position", data.get(position).position)
            nextIntent.putExtra("img_src", data.get(position).img_src)
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
        //bind : 사진 붙이는 함수입니다.
        holder.bind(board = data[position], context = context)
    }
}
package com.example.k_league_info.ui.community

import android.animation.ValueAnimator
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.RecyclerView
import com.example.k_league_info.*
import com.example.k_league_info.Scoredetail.HighlightModel
import com.example.k_league_info.ui.score.ScoreBoard
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_splash.*
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommunityAdapter(val context: Context, private val communityList: ArrayList<CommunityBoard>) :

    RecyclerView.Adapter<CommunityAdapter.Holder>() {
    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val content : TextView = itemView.findViewById(R.id.content)
        private val view : LinearLayout = itemView.findViewById(R.id.view)
        private val delete : ImageButton = itemView.findViewById(R.id.delete)
        private val password : EditText = itemView.findViewById(R.id.passwd)

        fun bind(communityBoard: CommunityBoard, context: Context) {

            title.text = communityBoard.title
            content.text = communityBoard.content
            view.visibility = View.GONE

            itemView.setOnClickListener {
                val height = view.resources.displayMetrics.density * 200
                if(view.visibility == View.GONE) {
                    val va = ValueAnimator.ofInt(0, height.toInt()).apply {
                        duration = 500
                        addUpdateListener{
                            val params = view.layoutParams
                            params.height = animatedValue as Int
                            view.layoutParams = params
                            view.visibility = View.VISIBLE
                        }
                    }
                    va.start()
                }
                else {
                    val va = ValueAnimator.ofInt(height.toInt(), 0).apply {
                        duration = 500
                        addUpdateListener{
                            val params = view.layoutParams
                            params.height = animatedValue as Int
                            view.layoutParams = params
                            view.visibility = View.GONE
                        }
                    }
                    va.start()
                }
            }

            delete.setOnClickListener {
                var pw = password.text.toString()
                if (pw == communityBoard.password) {
                    val json = JSONObject()
                    json.put("number", communityBoard.number)
                    AppData.restAPI.deleteCommunity(json).enqueue(object : Callback<ResponseBody> {
                        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                            Toast.makeText(context,"실패하였습니다.",Toast.LENGTH_LONG).show()
                        }
                        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                            if (response.code() == 200) {
                                Toast.makeText(context,"삭제되었습니다.",Toast.LENGTH_LONG).show()
                                AppData.communityList.remove(communityBoard)
                                notifyDataSetChanged()
                            } else {
                                Toast.makeText(context,"실패하였습니다.",Toast.LENGTH_LONG).show()
                            }
                        }
                    })
                } else {
                    Toast.makeText(context, "비밀번호가 틀렸습니다.", Toast.LENGTH_LONG).show()
                }
                val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_communityboard, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return communityList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(communityList[position], context)
    }
}
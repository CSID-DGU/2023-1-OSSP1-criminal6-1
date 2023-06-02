import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.testapplication.*
import com.example.testapplication.service.APIS
import retrofit2.Call
import retrofit2.Response


class MyAdapter(private var roomList:  List<getRoomRecommendationModel.recommendationRoomInfo>
): RecyclerView.Adapter<MyAdapter.RecyclerViewViewHolder>() {
    private val api = APIS.create()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_itemrecyclerciew, parent, false)
        return RecyclerViewViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerViewViewHolder, position: Int) {
        holder.bind(roomList[position])

        holder.itemView.setOnClickListener {
            api.addUserToChatRoom(
                roomList[position].id.toString(),
                roomList[position].roomID
            ).enqueue(object : retrofit2.Callback<Void> {
                override fun onResponse(
                    call: Call<Void>,
                    response: Response<Void>
                ) {
                    Log.d("addUserSuccess", "addUserSuccess")
                    val intent = Intent(holder.itemView?.context, MainActivity::class.java)
                    ContextCompat.startActivity(holder.itemView.context, intent,null)
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.d("addUserFail", t.toString())
                }

            })
            Log.d("ididid", roomList[position].id.toString())
            Log.d("클릭","클릭")
        }
    }

    override fun getItemCount(): Int {
        return roomList.size
    }

    class RecyclerViewViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.room_name)
        private val Intro: TextView = itemView.findViewById(R.id.room_description)
            fun bind(recyclerViewItem: getRoomRecommendationModel.recommendationRoomInfo) {
                title.text = recyclerViewItem.title
                Intro.text= recyclerViewItem.roomIntro
            }
         }

    fun setRepoList(repoList: List<getRoomRecommendationModel.recommendationRoomInfo>){
        roomList = repoList.toList()
        notifyDataSetChanged()
    }

}






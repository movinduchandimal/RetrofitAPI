package com.example.myapplication03.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication03.R
import com.example.myapplication03.database.Photo
import com.squareup.picasso.Picasso
import retrofit2.Callback

class PhotoAdapter(
    private val photoList: List<Photo>,
    val context:Callback<List<Photo>>,
    private val onItemClicked:(position:Int)->Unit

):RecyclerView.Adapter<PhotoAdapter.ViewhHolder>(){
    class ViewhHolder(
        ItemView:View,
        private val onItemClicked: (position: Int) -> Unit
    ) :RecyclerView.ViewHolder(ItemView),View.OnClickListener{
        val imgView = itemView.findViewById<ImageView>(R.id.imgView)
        val txtTitle = itemView.findViewById<TextView>(R.id.txtTitle)
        val txtUrl = itemView.findViewById<TextView>(R.id.txtUrl)

        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(p0: View?) {
            val position = adapterPosition
            onItemClicked(position)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewhHolder {
        val view =
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.card_view_design,parent,false)

        return ViewhHolder(view,onItemClicked)
    }

    override fun onBindViewHolder(holder: ViewhHolder, position: Int) {
        val itemviewModel = photoList[position]

        Picasso.get()
            .load(itemviewModel.thumbnailUrl)
            .into(holder.imgView)
        holder.txtTitle.text = itemviewModel.title
        holder.txtUrl.text = itemviewModel.url
    }

    override fun getItemCount(): Int {
        return photoList.size
    }

}

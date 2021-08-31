package com.example.fredthephish

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class RecyclerAdapter(items : MutableList<DBEntries>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    private var titles = arrayOf("Fisch1", "Fisch2","Fisch3","Fisch4","Fisch5")
    private var details = arrayOf("detail1", "detail2", "detail3", "detail4", "detail5")
    private var images = intArrayOf(R.drawable.fisch, R.drawable.fisch, R.drawable.fisch, R.drawable.fisch, R.drawable.fisch)

    //creates card view, inflates it and passes it to the inner class
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(v)
    }

    var items = items

    //wie viele items man dem viewHolder gibt
    override fun getItemCount(): Int {
        //return titles.size
        return items.size/2
    }

    //populate data to cardView
    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        //holder.itemTitle.text = titles[position]
        //holder.itemDetail.text = details[position]
        holder.itemImage.setImageResource(images[position])

        holder.itemTitle.text = items[0].name
        holder.itemDetail.text = items[0].amount

    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView
        var itemTitle: TextView
        var itemDetail: TextView

        init{
            itemImage = itemView.findViewById(R.id.item_image)
            itemTitle = itemView.findViewById(R.id.item_title)
            itemDetail = itemView.findViewById(R.id.item_detail)


            itemView.setOnClickListener{
                val position: Int = absoluteAdapterPosition

                Toast.makeText(itemView.context, "you clicked on ${titles[position]}", Toast.LENGTH_SHORT).show()
            }

        }
        fun bind(i: Int, items : MutableList<String>){

            itemTitle.text = items[i]
            itemDetail.text = items[i+1]
        }
    }


}
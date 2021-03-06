package com.example.fredthephish

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import android.widget.Button
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

    var items: MutableList<DBEntries> = items

    //wie viele items man dem viewHolder gibt
    override fun getItemCount(): Int {
        //return titles.size
        return items.size
    }

    //populate data to cardView
    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        //holder.itemTitle.text = titles[position]
        //holder.itemDetail.text = details[position]

        holder.itemImage.setImageResource(images[position])
        holder.itemAmount.text = items[position].amount
        holder.itemTitle.text = items[position].name
        holder.itemDetail.text = items[position].description
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView
        var itemTitle: TextView
        var itemDetail: TextView
        var itemAmount: TextView
        var countDownButton: Button
        var countUpButton: Button

        init{
            itemImage = itemView.findViewById(R.id.item_image)
            itemTitle = itemView.findViewById(R.id.item_title)
            itemDetail = itemView.findViewById(R.id.item_detail)
            itemAmount = itemView.findViewById(R.id.TextFieldAmount)

            //count up and down buttons
            countUpButton = itemView.findViewById(R.id.CountUpButton)
            countDownButton = itemView.findViewById(R.id.CountDownButton)

            itemView.setOnClickListener{
                val position: Int = absoluteAdapterPosition

                Toast.makeText(itemView.context, "you clicked on ${titles[position]}", Toast.LENGTH_SHORT).show()
            }



        }
    }


}



package com.example.bitfitt

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BitFitAdapter (private val context: Context, private val days: MutableList<DAY>): RecyclerView.Adapter<BitFitAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{

        var nap: DAY? = null
        val dateTv = itemView.findViewById<TextView>(R.id.dateTv)
        val commentsTv = itemView.findViewById<TextView>(R.id.commentsTv)
        val hoursTv = itemView.findViewById<TextView>(R.id.hoursTv)
        val ratingTv = itemView.findViewById<TextView>(R.id.ratingTv)

        fun bind(day: DAY){
            dateTv.text = day.date
            commentsTv.text = day.comments
            hoursTv.text = day.hours.toString()
            ratingTv.text = day.rating.toString()
        }

        init{
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            Log.i("position click", "onclick successful on $adapterPosition")
        }
    }


    override fun getItemCount() = days.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.sleep_day, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pos = days[position]
        holder.nap = pos
        holder.dateTv.text = pos.date
        holder.commentsTv.text = pos.comments
        holder.ratingTv.text = pos.rating.toString()
        holder.hoursTv.text = pos.hours.toString()
        holder.bind(pos)
    }
}
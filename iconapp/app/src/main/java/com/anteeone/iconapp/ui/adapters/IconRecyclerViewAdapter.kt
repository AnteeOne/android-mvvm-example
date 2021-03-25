package com.anteeone.iconapp.ui.adapters

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.anteeone.iconapp.R
import com.anteeone.iconapp.domain.entities.IconListEntity
import com.squareup.picasso.Picasso


class IconRecyclerViewAdapter(
    private val onClick: (Int) -> Unit
) : RecyclerView.Adapter<IconRecyclerViewAdapter.ViewHolder>() {

    private var values: List<IconListEntity.IconListMember> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.icon_item, parent, false)
        return ViewHolder(view,onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.bind(values[position])

    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(val view: View, private val onClick: (Int) -> Unit) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.icon_list_item_title)
        val isPremiumView: TextView = view.findViewById(R.id.icon_list_item_is_premium)
        val logoView: ImageView = view.findViewById(R.id.icon_list_item_logo)

        fun bind(item: IconListEntity.IconListMember){
            idView.text = item.iconId.toString()
            isPremiumView.text = item.isPremium.toString()
            Picasso.get()
                .load(item.previewUrl)
                .fit()
                .into(logoView)
            logoView.setOnClickListener{
                onClick(item.iconId)
                Log.println(Log.DEBUG,"anteetag","falsed touched on item view ")
            }
        }

    }

    fun submitData(valueList: List<IconListEntity.IconListMember>){
        values = valueList
        notifyDataSetChanged()
    }
}
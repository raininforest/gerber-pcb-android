package com.github.raininforest.gerberpcb.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.recyclerview.widget.RecyclerView
import com.github.raininforest.gerberpcb.R
import com.github.raininforest.gerberpcb.model.GerberItem
import com.github.raininforest.gerberpcb.model.GerberList

class GerberListAdapter : RecyclerView.Adapter<GerberListAdapter.GerberListViewHolder>() {

    private var gerberList = GerberList()

    fun setList(list: GerberList) {
        gerberList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GerberListViewHolder {
        return GerberListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
                    as View
        )
    }

    override fun onBindViewHolder(holder: GerberListViewHolder, position: Int) {
        holder.bind(gerberList.get(position))
    }

    override fun getItemCount() = gerberList.getSize()

    inner class GerberListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(gerberItem: GerberItem) {
            val checkBox = itemView.findViewById<AppCompatCheckBox>(R.id.itemCheckBox)
            checkBox.text = gerberItem.name
            checkBox.isChecked = gerberItem.visible
            //TODO установка листенера на чекбокс
        }
    }
}
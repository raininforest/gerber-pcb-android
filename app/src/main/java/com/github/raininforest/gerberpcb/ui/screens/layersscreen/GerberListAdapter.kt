package com.github.raininforest.gerberpcb.ui.screens.layersscreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.raininforest.gerberpcb.databinding.ItemViewBinding

/**
 * Adapter for recycler view list with gerber files
 *
 * Created by Sergey Velesko on 18.07.2021
 */
class GerberListAdapter(
    private val onItemChecked: (id: String, checked: Boolean) -> Unit
) :
    RecyclerView.Adapter<GerberListAdapter.GerberListViewHolder>() {

    private var gerberList = mutableListOf<GerberItemUi>()

    fun setList(list: List<GerberItemUi>) {
        gerberList.clear()
        gerberList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GerberListViewHolder {
        val itemBinding = ItemViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return GerberListViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: GerberListViewHolder, position: Int) {
        holder.bind(gerberList[position])
    }

    override fun getItemCount() = gerberList.size

    inner class GerberListViewHolder(private val itemBinding: ItemViewBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(gerberItem: GerberItemUi) {
            val checkBox = itemBinding.itemCheckBox
            checkBox.text = gerberItem.name
            checkBox.isChecked = gerberItem.checked
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                onItemChecked.invoke(gerberItem.id, isChecked)
            }
        }
    }
}
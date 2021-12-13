package com.github.raininforest.gerberpcb.ui.screens.layersscreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.raininforest.gerberpcb.databinding.ItemViewBinding

/**
 * Adapter for recycler view list with gerber files
 *
 * Created by Sergey Velesko on 18.07.2021
 */
class GerberListAdapter(
    private val onItemChecked: (id: String, checked: Boolean) -> Unit
) : ListAdapter<GerberItemUi, GerberListAdapter.GerberItemViewHolder>(GerberDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GerberItemViewHolder {
        val itemBinding = ItemViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return GerberItemViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: GerberItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun getGerberItemId(position: Int): String = currentList[position].id

    inner class GerberItemViewHolder(private val itemBinding: ItemViewBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(gerberItem: GerberItemUi) {
            val checkBox = itemBinding.itemCheckBox
            checkBox.text = gerberItem.name
            checkBox.isChecked = gerberItem.checked
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                onItemChecked.invoke(gerberItem.id, isChecked)
            }

            itemBinding.itemColorButton.background.setTint(gerberItem.color)
        }
    }

    class GerberDiffCallback : DiffUtil.ItemCallback<GerberItemUi>() {
        override fun areItemsTheSame(oldItem: GerberItemUi, newItem: GerberItemUi): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: GerberItemUi, newItem: GerberItemUi): Boolean =
            oldItem.id == newItem.id && oldItem.name == newItem.name
    }
}
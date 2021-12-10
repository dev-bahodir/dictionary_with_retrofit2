package dev.bahodir.retrofitapifirsttask.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.bahodir.retrofitapifirsttask.databinding.ItemHomeBinding
import dev.bahodir.retrofitapifirsttask.user.UserOneItem

class RVAdapter : ListAdapter<UserOneItem, RVAdapter.VH>(DU()) {

    class DU : DiffUtil.ItemCallback<UserOneItem>(){
        override fun areItemsTheSame(oldItem: UserOneItem, newItem: UserOneItem): Boolean {
            return oldItem.word == newItem.word
        }

        override fun areContentsTheSame(oldItem: UserOneItem, newItem: UserOneItem): Boolean {
            return oldItem == newItem
        }
    }

    inner class VH(var binding: ItemHomeBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(userOneItem: UserOneItem, position: Int) {
            binding.name.text = userOneItem.meanings[position].partOfSpeech
            binding.synonym.text = userOneItem.meanings[position].partOfSpeech
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(getItem(position), position)
    }
}
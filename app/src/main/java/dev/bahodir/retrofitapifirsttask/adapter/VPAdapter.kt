package dev.bahodir.retrofitapifirsttask.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.bahodir.retrofitapifirsttask.R
import dev.bahodir.retrofitapifirsttask.databinding.FragmentSplashBinding
import dev.bahodir.retrofitapifirsttask.databinding.VpLayoutBinding

class VPAdapter(var list: List<Int>) : RecyclerView.Adapter<VPAdapter.VH>() {

    inner class VH(var binding: VpLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(VpLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.actionImage.setImageResource(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
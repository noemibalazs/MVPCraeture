package com.example.mvpcraeture.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvpcraeture.R
import com.example.mvpcraeture.creaturehelper.SharedHelper
import com.example.mvpcraeture.model.Creature

class CreatureAdapter(
    val creatures: MutableList<Creature>,
    private val sharedHelper: SharedHelper?
) :
    RecyclerView.Adapter<CreatureAdapter.CreatureVH>() {

    var listener: CreatureListener? = null

    class CreatureVH(view: View, val listener: CreatureListener?, val sharedHelper: SharedHelper?) :
        RecyclerView.ViewHolder(view) {
        val name = view.findViewById<AppCompatTextView>(R.id.tv_avatar_name)
        val avatar = view.findViewById<ImageView>(R.id.iv_all_avatar)

        fun bind(creature: Creature) {
            name.text = creature.name
            avatar.setImageResource(creature.drawable)
            itemView.setOnClickListener {
                listener?.onCreatureClicked(creature)
                sharedHelper?.saveCreatureName(creature.name)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreatureVH {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_all_creature, parent, false)
        return CreatureVH(view, listener, sharedHelper)
    }

    override fun getItemCount(): Int {
        return creatures.size
    }

    override fun onBindViewHolder(holder: CreatureVH, position: Int) {
        holder.bind(creatures[position])
    }

    fun removeListener() {
        listener = null
    }
}
package com.example.mvpcraeture.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvpcraeture.R

class AvatarAdapter(val avatarList: MutableList<Avatar>) :
    RecyclerView.Adapter<AvatarAdapter.AvatarVH>() {

    var avatarListener: AvatarListener? = null

    class AvatarVH(view: View, val avatarListener: AvatarListener?) :
        RecyclerView.ViewHolder(view) {
        val avatarImage = view.findViewById<ImageView>(R.id.iv_my_avatar)

        fun onBind(avatar: Avatar) {
            avatarImage.setImageResource(avatar.drawable)
            itemView.setOnClickListener {
                avatarListener?.let {
                    it.onAvatarClicked(avatar.drawable)
                    Log.d("Avatar adapter", "id: ${avatar.drawable}")
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AvatarVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_avatar, parent, false)
        return AvatarVH(view, avatarListener)
    }

    override fun getItemCount(): Int {
        return avatarList.size
    }

    override fun onBindViewHolder(holder: AvatarVH, position: Int) {
        holder.onBind(avatarList[position])
    }

    fun removeListener() {
        avatarListener == null
    }
}
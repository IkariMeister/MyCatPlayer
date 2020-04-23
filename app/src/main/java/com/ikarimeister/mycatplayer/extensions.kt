package com.ikarimeister.mycatplayer

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

inline fun Context.toast(message: String) =
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()

inline fun RecyclerView.ViewHolder.toast(message: String) =
        this.itemView.context.toast(message)

inline fun ViewGroup.inflate(resId: Int): View =
        LayoutInflater.from(this.context).inflate(resId, this, false)
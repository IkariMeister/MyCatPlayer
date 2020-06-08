package com.ikarimeister.mycatplayer

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

inline fun Context.toast(message: String) =
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()

inline fun RecyclerView.ViewHolder.toast(message: String) =
        this.itemView.context.toast(message)

inline fun ViewGroup.inflate(@LayoutRes resId: Int): View =
        LayoutInflater.from(this.context).inflate(resId, this, false)

inline fun ImageView.loadUrl(url: String) =
        Glide.with(this).load(url).into(this)

inline fun <reified T : Activity> Context.startActivity(vararg pair: Pair<String, Any>) =
        @Suppress("SpreadOperator")
        Intent(this, T::class.java)
                .apply { putExtras(bundleOf(*pair)) }
                .also(::startActivity)
package com.example.myapplication

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.*
import kotlinx.android.synthetic.main.item.view.*
import java.util.*


/**
 * Created by KimYounSug750 on 25/09/2019.
 */
class RecyclerViewAdapter(val mainActivity: MainActivity) : RecyclerView.Adapter<Module_Helper>() {

    val baseCalendar =Module_Base()
    init {
        baseCalendar.initBaseCalendar {
            refreshView(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Module_Helper {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return Module_Helper(view)
    }

    override fun getItemCount(): Int {
        return Module_Base.LOW_OF_CALENDAR * Module_Base.DAYS_OF_WEEK
    }

    override fun onBindViewHolder(holder: Module_Helper, position: Int)  {

        if (position % Module_Base.DAYS_OF_WEEK == 0) holder.containerView.item_date.setTextColor(Color.parseColor("#ff1200"))
        else holder.containerView.item_date.setTextColor(Color.parseColor("#676d6e"))

        if (position < baseCalendar.prevMonthTailOffset || position >= baseCalendar.prevMonthTailOffset + baseCalendar.currentMonthMaxDate) {
            holder.containerView.item_date.alpha=0.3f
        } else {
            holder.containerView.item_date.alpha = 1f
        }
        holder.containerView.item_date.text = baseCalendar.data[position].toString()
    }

    fun changeToPrevMonth() {
        baseCalendar.changeToPrevMonth {
            refreshView(it)
        }
    }

    fun changeToNextMonth() {
        baseCalendar.changeToNextMonth {
            refreshView(it)
        }
    }

    private fun refreshView(calendar: Calendar) {
        notifyDataSetChanged()
        mainActivity.refreshCurrentMonth(calendar)
    }
}
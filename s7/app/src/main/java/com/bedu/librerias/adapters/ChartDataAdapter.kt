package com.bedu.librerias.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bedu.librerias.items.ChartItem


class ChartDataAdapter internal constructor(context: Context?, objects: List<ChartItem?>?) :
    ArrayAdapter<ChartItem?>(context!!, 0, objects!!) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getItem(position)!!.getView(position, convertView, context)!!
    }

    override fun getItemViewType(position: Int): Int {
        val ci = getItem(position)
        return ci?.itemType ?: 0
    }

    override fun getViewTypeCount(): Int {
        return 3
    }
}
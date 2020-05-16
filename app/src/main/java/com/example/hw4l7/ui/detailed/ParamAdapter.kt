package com.example.hw4l7.ui.detailed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hw4l7.R
import com.example.hw4l7.domain.RemoteProduct
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.param_item.*

class ParamAdapter : RecyclerView.Adapter<ParamAdapter.ViewHolder>() {

    private var params: List<RemoteProduct.Attribute> = listOf()

    fun setData(params: List<RemoteProduct.Attribute>) {
        this.params = params
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.param_item, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(params[position])
    }

    inner class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(param: RemoteProduct.Attribute) {
            paramTv.text = param.name + ": " + param.value
        }
    }

    override fun getItemCount(): Int = params.size
}
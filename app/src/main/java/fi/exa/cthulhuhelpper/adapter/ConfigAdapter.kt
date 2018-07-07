package fi.exa.cthulhuhelpper.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import fi.exa.cthulhuhelpper.R
import fi.exa.cthulhuhelpper.model.CthulhuToken
import fi.exa.cthulhuhelpper.model.Difficulty
import kotlinx.android.synthetic.main.crementer.view.*
import kotlinx.android.synthetic.main.view_configuration_header.view.*

private const val ItemType = 1
private const val HeaderType = 2

class ConfigAdapter(private val configChanged: (CthulhuToken, Int) -> Unit,
                    private val onDifficutySelected: (Difficulty) -> Unit):
        RecyclerView.Adapter<RecyclerView.ViewHolder>(), AdapterView.OnItemSelectedListener {
    override fun onNothingSelected(p0: AdapterView<*>?) {}

    override fun onItemSelected(adapterView: AdapterView<*>, p1: View?, position: Int, id: Long) {
        val item = adapterView.selectedItem as String
        onDifficutySelected(Difficulty.valueOf(item))
    }

    private lateinit var headerAdapter: ArrayAdapter<String>
    private val tokens = mutableListOf<Pair<CthulhuToken, Int>>()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
           ItemType -> ItemViewHolder(LayoutInflater.from(parent?.context)
                   .inflate(R.layout.crementer, parent, false))
           HeaderType -> {
               val viewHolder = HeaderViewHolder(LayoutInflater.from(parent?.context)
                   .inflate(R.layout.view_configuration_header, parent, false))
               headerAdapter = ArrayAdapter(parent?.context, android.R.layout.simple_spinner_item)
               headerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
               viewHolder.spinner.adapter = headerAdapter
               return viewHolder
           }
           else -> throw IndexOutOfBoundsException("Unknown viewholder type")
        }
    }

    override fun getItemCount()  = tokens.size + 1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if(holder is ItemViewHolder){
            setHolderValues(holder, position)
        }
        else if(holder is HeaderViewHolder){
            headerAdapter.clear()
            headerAdapter.addAll(Difficulty.values().map { it.toString() })
            holder.spinner.onItemSelectedListener = this
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            HeaderType
        } else ItemType
    }

    private fun setHolderValues(viewHolder: ItemViewHolder, position: Int){
        val position = position - 1
        viewHolder.tokenCountView.text = tokens[position].second.toString()
        viewHolder.tokenLabelView.text = tokens[position].first.stringName
        viewHolder.incrementButton.setOnClickListener { _ ->
            configChanged(tokens[position].first, tokens[position].second + 1)}
        viewHolder.decrementButton.setOnClickListener { _ ->
            configChanged(tokens[position].first, tokens[position].second - 1)}
    }

    fun updateAdapterValues(newTokens: List<Pair<CthulhuToken, Int>>){
        tokens.clear()
        tokens.addAll(newTokens)
        notifyDataSetChanged()
    }
}

private class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val incrementButton = view.increment!!
    val decrementButton = view.decrement!!
    val tokenCountView = view.token_count!!
    val tokenLabelView = view.token_label!!
}
private class HeaderViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val spinner = view.difficulty_spinner!!
}
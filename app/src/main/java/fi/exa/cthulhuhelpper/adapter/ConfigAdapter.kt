package fi.exa.cthulhuhelpper.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import androidx.recyclerview.widget.RecyclerView
import fi.exa.cthulhuhelpper.R
import fi.exa.cthulhuhelpper.model.CthulhuToken
import fi.exa.cthulhuhelpper.model.Difficulty
import kotlinx.android.synthetic.main.crementer.view.*
import kotlinx.android.synthetic.main.view_configuration_header.view.*

private const val ItemType = 1
private const val HeaderType = 2

class ConfigAdapter(private val configChanged: (CthulhuToken, Int) -> Unit,
                    private val onDifficultySelected: (Difficulty) -> Unit):
        RecyclerView.Adapter<RecyclerView.ViewHolder>(), AdapterView.OnItemSelectedListener {


    private lateinit var headerAdapter: DifficultyAdapter
    private lateinit var difficultySpinner: Spinner
    private val tokens = mutableListOf<Pair<CthulhuToken, Int>>()

    override fun onNothingSelected(p0: AdapterView<*>?) {}

    override fun onItemSelected(adapterView: AdapterView<*>, p1: View?, position: Int, id: Long) {
        if(headerAdapter.difficultySelected(position)){
            val item = adapterView.selectedItem as String
            onDifficultySelected(Difficulty.valueOf(item))
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
           ItemType -> ItemViewHolder(LayoutInflater.from(parent.context)
                   .inflate(R.layout.crementer, parent, false))
           HeaderType -> {
               val viewHolder = HeaderViewHolder(LayoutInflater.from(parent.context)
                   .inflate(R.layout.view_configuration_header, parent, false))
               headerAdapter = DifficultyAdapter(parent.context, android.R.layout.simple_spinner_item)
               headerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
               viewHolder.spinner.adapter = headerAdapter
               return viewHolder
           }
           else -> throw IndexOutOfBoundsException("Unknown viewholder type")
        }
    }

    override fun getItemCount()  = tokens.size + 1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ItemViewHolder){
            setHolderValues(holder, position)
        }
        else if(holder is HeaderViewHolder){
            holder.spinner.onItemSelectedListener = this
            difficultySpinner = holder.spinner
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
        viewHolder.incrementButton.setOnClickListener { _ -> difficultyAdjusted(position, 1)}
        viewHolder.decrementButton.setOnClickListener { _ -> difficultyAdjusted(position, -1)}
    }

    fun updateAdapterValues(newTokens: List<Pair<CthulhuToken, Int>>, difficulty: Difficulty?){
        if( difficulty == null ){
            headerAdapter.setCustomDifficulty()
            difficultySpinner.setSelection(4)
        }
        else {
            when(difficulty){
                Difficulty.Easy -> difficultySpinner.setSelection(0)
                Difficulty.Standard -> difficultySpinner.setSelection(1)
                Difficulty.Hard -> difficultySpinner.setSelection(2)
                Difficulty.Expert -> difficultySpinner.setSelection(3)
            }
        }
        tokens.clear()
        tokens.addAll(newTokens)
        notifyDataSetChanged()
    }

    private fun difficultyAdjusted(position: Int, by: Int){
        val pair = tokens[position]
        configChanged(pair.first, pair.second + by)
        headerAdapter.setCustomDifficulty()
        difficultySpinner.setSelection(4)

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
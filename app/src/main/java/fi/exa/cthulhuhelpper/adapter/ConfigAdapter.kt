package fi.exa.cthulhuhelpper.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fi.exa.cthulhuhelpper.R
import fi.exa.cthulhuhelpper.model.CthulhuToken
import kotlinx.android.synthetic.main.crementer.view.*

class ConfigAdapter(private val configChanged: (CthulhuToken, Int) -> Unit):
        RecyclerView.Adapter<ConfigAdapter.ViewHolder>() {

    private val tokens = mutableListOf<Pair<CthulhuToken, Int>>()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context)
                .inflate(R.layout.crementer, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount()  = tokens.size


    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.let { h -> setHolderValues(h, position) }
    }

    private fun setHolderValues(viewHolder: ViewHolder, position: Int){
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

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val incrementButton = view.increment!!
        val decrementButton = view.decrement!!
        val tokenCountView = view.token_count!!
        val tokenLabelView = view.token_label!!
    }

}